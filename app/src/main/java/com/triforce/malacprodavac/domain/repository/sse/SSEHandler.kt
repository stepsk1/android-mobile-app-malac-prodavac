package com.triforce.malacprodavac.domain.repository.sse

import com.launchdarkly.eventsource.MessageEvent

interface SSEHandler {
    fun onSSEConnectionOpened()
    fun onSSEConnectionClosed()
    fun onSSEEventReceived(event: String, messageEvent: MessageEvent)
    fun onSSEError(t: Throwable)
}