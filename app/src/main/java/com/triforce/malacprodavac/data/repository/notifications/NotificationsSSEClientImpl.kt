package com.triforce.malacprodavac.data.repository.notifications

import com.launchdarkly.eventsource.EventSource
import com.launchdarkly.logging.LDLogger
import com.launchdarkly.logging.LDSLF4J
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsSSEClient
import com.triforce.malacprodavac.domain.repository.sse.SSEClient
import com.triforce.malacprodavac.domain.repository.sse.SSEHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.internal.closeQuietly
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsSSEClientImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
) : NotificationsSSEClient(okHttpClient) {
    override var eventSourceSse: EventSource? = null
    override var sseHandlers: SSEHandler? = null

    override fun initSse(sseHandler: SSEHandler, errorCallback: (Throwable) -> Unit) {
        this.sseHandlers = sseHandler
        val eventHandler = sseHandlers?.let { DefaultEventHandler(it) }
        try {
            val newClientBuilder =
                okHttpClient.newBuilder()
            newClientBuilder.interceptors()
                .remove(okHttpClient.interceptors.find { it is HttpLoggingInterceptor })
            val newClient = newClientBuilder.build()

            eventSourceSse = EventSource.Builder(
                eventHandler, SUBSCRIBE_URL
            )
                .client(newClient)
                .logger(
                    LDLogger.withAdapter(LDSLF4J.adapter(), SSEClient::class.simpleName)
                )
                .build()

            eventSourceSse?.start()
        } catch (e: Exception) {
            errorCallback(e)
        }
    }

    override fun disconnect() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                eventSourceSse?.closeQuietly()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}