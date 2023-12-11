package com.triforce.malacprodavac

import android.os.Build
import android.util.Log
import com.launchdarkly.eventsource.EventHandler
import com.launchdarkly.eventsource.EventSource
import com.launchdarkly.eventsource.MessageEvent
import com.triforce.malacprodavac.data.remote.auth.interceptors.AuthInterceptorImpl
import com.triforce.malacprodavac.data.remote.notifications.NotificationsApi
import com.triforce.malacprodavac.data.services.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.internal.closeQuietly
import okhttp3.logging.HttpLoggingInterceptor
import java.net.URI
import java.time.Duration
import javax.inject.Inject

class SSEClient @Inject constructor(
    private val client: OkHttpClient,
    private val sessionManager: SessionManager
) {
    private var sseHandlers: SSEHandler? = null
    private var eventSourceSse: EventSource? = null

    fun initSse(sseHandler: SSEHandler, errorCallback: (Throwable) -> Unit) {
        this.sseHandlers = sseHandler
        val eventHandler = sseHandlers?.let { DefaultEventHandler(it) }
        val baseUrl = NotificationsApi.ROUTE
        val PATH = "/subscribe" // Replace with the SSE endpoint path
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                eventSourceSse = EventSource.Builder(
                    eventHandler, URI.create(baseUrl.plus(PATH))
                ).client(
                    OkHttpClient.Builder().addInterceptor(AuthInterceptorImpl(sessionManager))
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                )
                    .connectTimeout(Duration.ofSeconds(3))
                    .backoffResetThreshold(Duration.ofSeconds(3))
                    .build()

                eventSourceSse?.let {
                    it.start()
                }
            }
        } catch (e: Exception) {
            errorCallback(e)
        }
    }

    private class DefaultEventHandler(private val sseHandler: SSEHandler) : EventHandler {
        override fun onOpen() {
            Log.i("SSE_CONNECTION", "OPEN")
            sseHandler.onSSEConnectionOpened()
        }

        override fun onClosed() {
            Log.i("SSE_CONNECTION", "CLOSED")
            sseHandler.onSSEConnectionClosed()
        }

        override fun onMessage(event: String, messageEvent: MessageEvent) {
            Log.i("SSE_CONNECTION", "MESSAGE")
            sseHandler.onSSEEventReceived(event, messageEvent)
        }

        override fun onError(t: Throwable) {
            Log.i("SSE_CONNECTION", "ERROR")
            sseHandler.onSSEError(t)
        }

        override fun onComment(comment: String) {
            Log.i("SSE_CONNECTION", comment)
        }
    }

    fun disconnect() {
        Log.d("SSE_CONNECTION", "CLOSED")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                eventSourceSse?.let {
                    it.closeQuietly()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
