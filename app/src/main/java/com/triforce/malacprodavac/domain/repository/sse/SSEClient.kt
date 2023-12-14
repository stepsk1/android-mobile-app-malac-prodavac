package com.triforce.malacprodavac.domain.repository.sse

import com.launchdarkly.eventsource.EventHandler
import com.launchdarkly.eventsource.EventSource
import com.launchdarkly.eventsource.MessageEvent
import okhttp3.OkHttpClient

abstract class SSEClient(private val okHttpClient: OkHttpClient) {
    abstract var sseHandlers: SSEHandler?
    abstract var eventSourceSse: EventSource?

    abstract fun initSse(sseHandler: SSEHandler, errorCallback: (Throwable) -> Unit)

    class DefaultEventHandler(private val sseHandler: SSEHandler) : EventHandler {
        override fun onOpen() {
            sseHandler.onSSEConnectionOpened()
        }

        override fun onClosed() {
            sseHandler.onSSEConnectionClosed()
        }

        override fun onMessage(event: String, messageEvent: MessageEvent) {
            sseHandler.onSSEEventReceived(event, messageEvent)
        }

        override fun onError(t: Throwable) {
            sseHandler.onSSEError(t)
        }

        override fun onComment(comment: String) = Unit
    }

    abstract fun disconnect()
}