package com.triforce.malacprodavac.data.remote

import android.util.Log
import com.triforce.malacprodavac.MainActivity
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener


class EventSourceListenerImpl : EventSourceListener() {
    override fun onOpen(eventSource: EventSource, response: Response) {
        super.onOpen(eventSource, response)
        Log.d(MainActivity::class.simpleName, "Connection Opened")
    }

    override fun onClosed(eventSource: EventSource) {
        super.onClosed(eventSource)
        Log.d(MainActivity::class.simpleName, "Connection Closed")
    }

    override fun onEvent(
        eventSource: EventSource,
        id: String?,
        type: String?,
        data: String
    ) {
        super.onEvent(eventSource, id, type, data)
        Log.d(MainActivity::class.simpleName, "On Event Received! Data -: $data")
    }

    override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
        super.onFailure(eventSource, t, response)
        Log.d(MainActivity::class.simpleName, "On Failure -: ${response?.body}")
    }
}