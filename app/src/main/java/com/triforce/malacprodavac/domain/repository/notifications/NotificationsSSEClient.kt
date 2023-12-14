package com.triforce.malacprodavac.domain.repository.notifications

import com.triforce.malacprodavac.data.remote.notifications.NotificationsApi
import com.triforce.malacprodavac.domain.repository.sse.SSEClient
import okhttp3.OkHttpClient
import java.net.URI

abstract class NotificationsSSEClient(okHttpClient: OkHttpClient) : SSEClient(okHttpClient) {
    companion object {
        val SUBSCRIBE_URL: URI = URI.create(NotificationsApi.ROUTE.plus("/subscribe"))
    }
}