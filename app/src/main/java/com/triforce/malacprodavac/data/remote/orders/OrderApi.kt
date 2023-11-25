package com.triforce.malacprodavac.data.remote.orders

import com.triforce.malacprodavac.data.local.order.OrderEntity
import com.triforce.malacprodavac.data.local.schedulePickups.SchedulePickupsEntity
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import com.triforce.malacprodavac.data.remote.orders.dto.CreateOrderDto
import com.triforce.malacprodavac.data.remote.orders.dto.CreateSchedulePickupDto
import com.triforce.malacprodavac.data.remote.orders.dto.UpdateOrderDto
import com.triforce.malacprodavac.data.remote.orders.dto.UpdateScheduledPickupDto
import com.triforce.malacprodavac.domain.model.UpdateScheduledPickup
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {

    @POST(ROUTE)
    suspend fun create(@Body() createPostDto: CreateOrderDto): OrderEntity

    @GET(ROUTE)
    suspend fun getOrders(): PaginationResponse<OrderEntity>

    @GET("${ROUTE}/{id}")
    suspend fun getOrder(@Path("id") id: Int): OrderEntity

    @PATCH("${ROUTE}/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body() updateProductDto: UpdateOrderDto
    ): OrderEntity

    @DELETE("${ROUTE}/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): OrderEntity

    @POST("${ROUTE}/{id}/scheduledPickups")
    suspend fun createSchedulePickups(
        @Path("id") id: Int,
        @Body() createSchedulePickupDto: CreateSchedulePickupDto
    ) : SchedulePickupsEntity

    @PATCH("${ROUTE}/{id}/scheduledPickups/{scheduledPickupId}")
    suspend fun updateSchedulePickups(
        @Path("id") id: Int,
        @Path("scheduledPickupId") scheduledPickupId: Int,
        @Body() updateScheduledDto: UpdateScheduledPickupDto
    ) : SchedulePickupsEntity


    companion object {
        const val ROUTE = "/orders"
    }
}