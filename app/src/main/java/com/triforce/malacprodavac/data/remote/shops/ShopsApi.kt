package com.triforce.malacprodavac.data.remote.shops

import com.triforce.malacprodavac.data.local.schedulePickups.SchedulePickupsEntity
import com.triforce.malacprodavac.data.remote.Api
import com.triforce.malacprodavac.data.remote.customers.CustomersApi
import com.triforce.malacprodavac.data.remote.shops.dto.CreateShopDto
import com.triforce.malacprodavac.domain.model.PaginationResponse
import com.triforce.malacprodavac.domain.model.SchedulePickup
import com.triforce.malacprodavac.domain.model.Shop
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShopsApi {
    @POST(ROUTE)
    suspend fun registerShop(
        @Body registerRequest: CreateShopDto
    ): Shop

    @GET("${ROUTE}/{id}/scheduledPickups")
    suspend fun getShopSchedulePickups(
        @Path("id") shopId: Int,
        //@QueryMap queryMap: MutableMap<String, String>
    ): PaginationResponse<SchedulePickupsEntity>

    @GET("${ROUTE}/{id}/scheduledPickups/{scheduledPickupId}")
    suspend fun getShopSchedulePickupsById(
        @Path("id") shopId: Int,
        @Path("scheduledPickupId") scheduledPickupId: Int
        //@QueryMap queryMap: MutableMap<String, String>
    ): SchedulePickupsEntity

    companion object{
        const val ROUTE="/shops"
    }
}