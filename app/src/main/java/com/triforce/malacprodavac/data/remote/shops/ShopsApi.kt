package com.triforce.malacprodavac.data.remote.shops

import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.data.remote.Api
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import com.triforce.malacprodavac.data.remote.shops.dto.CreateShopDto
import com.triforce.malacprodavac.domain.model.Shop
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ShopsApi {

    @POST(ROUTE)
    suspend fun registerShop(
        @Body registerRequest: CreateShopDto
    ): Shop

    @GET(ROUTE)
    suspend fun getShops(@QueryMap() queryMap: MutableMap<String, String>): PaginationResponse<ShopEntity>

    @GET("${ROUTE}/{id}")
    suspend fun getShop(@Path("id") id: Int): ShopEntity

    companion object{
        const val ROUTE="/shops"
    }
}