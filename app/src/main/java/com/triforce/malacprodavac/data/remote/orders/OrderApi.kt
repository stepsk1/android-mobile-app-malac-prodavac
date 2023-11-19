package com.triforce.malacprodavac.data.remote.orders

import com.triforce.malacprodavac.data.local.order.OrderEntity
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import com.triforce.malacprodavac.data.remote.orders.dto.CreateOrderDto
import com.triforce.malacprodavac.data.remote.orders.dto.UpdateOrderDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

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

    companion object {
        const val ROUTE = "/orders"
    }
}