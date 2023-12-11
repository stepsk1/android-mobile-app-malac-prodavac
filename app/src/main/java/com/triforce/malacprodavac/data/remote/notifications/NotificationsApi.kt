package com.triforce.malacprodavac.data.remote.notifications

import com.triforce.malacprodavac.data.remote.Api
import com.triforce.malacprodavac.domain.model.PaginationResponse
import com.triforce.malacprodavac.domain.model.notifications.Notification
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming
import java.io.InputStream

interface NotificationsApi {

    @GET("$ROUTE/subscribe")
    suspend fun subscribe(): Call<InputStream>

    @GET(ROUTE)
    suspend fun getNotifications(): PaginationResponse<Notification>

    @GET("${ROUTE}/{id}")
    suspend fun getNotification(@Path("id") id: Int): Notification

    companion object {
        const val ROUTE = "${Api.BASE_URL}/notifications"
    }
}