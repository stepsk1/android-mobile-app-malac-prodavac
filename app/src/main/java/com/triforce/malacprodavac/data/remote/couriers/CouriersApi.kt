package com.triforce.malacprodavac.data.remote.couriers

import com.triforce.malacprodavac.data.remote.Api
import com.triforce.malacprodavac.data.remote.couriers.dto.CreateCourierDto
import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.PaginationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface CouriersApi {
    @POST(ROUTE)
    suspend fun registerCouriers(
        @Body registerRequest: CreateCourierDto
    ): Courier

    @GET(ROUTE)
    suspend fun getCouriers(@QueryMap queryMap: MutableMap<String, String>):PaginationResponse<Courier>

    companion object {
        const val ROUTE = "/couriers"
    }
}