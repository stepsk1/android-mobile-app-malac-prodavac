package com.triforce.malacprodavac.data.remote.notifications

import com.triforce.malacprodavac.data.remote.Api
import retrofit2.http.GET
import retrofit2.http.Streaming

interface NotificationsApi {

    @Streaming
    @GET("$ROUTE/subscribe")
    suspend fun subscribe(): Unit

    companion object {
        const val ROUTE = "${Api.BASE_URL}/notifications"
    }
}