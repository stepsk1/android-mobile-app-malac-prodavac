package com.triforce.malacprodavac.data.remote.notifications

import com.triforce.malacprodavac.data.remote.Api
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import com.triforce.malacprodavac.domain.model.notifications.Notification
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface NotificationsApi {
    @GET(ROUTE)
    suspend fun getNotifications(
        @QueryMap queryMap: MutableMap<String, String> = mutableMapOf<String, String>()
    ): PaginationResponse<Notification>

    @GET("${ROUTE}/{id}")
    suspend fun getNotification(@Path("id") id: Int): Notification

    companion object {
        const val ROUTE = "${Api.BASE_URL}/notifications"
    }
}