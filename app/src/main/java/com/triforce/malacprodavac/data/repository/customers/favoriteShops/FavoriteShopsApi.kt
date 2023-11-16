package com.triforce.malacprodavac.data.repository.customers.favoriteShops

import com.triforce.malacprodavac.data.remote.customers.CustomersApi
import com.triforce.malacprodavac.data.repository.customers.favoriteShops.dto.CreateFavoriteShopDto
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteShopsApi {
    @POST(ROUTE)
    suspend fun createFavoriteShop(
        @Path("id") customerId: Int,
        @Body createFavoriteShopDto: CreateFavoriteShopDto
    ): FavoriteShop

    @GET(ROUTE)
    suspend fun getFavoriteShops(
        @Path("id") customerId: Int,
    ): List<FavoriteShop>

    @DELETE("${ROUTE}/{favoriteShopId}")
    suspend fun deleteFavoriteShop(
        @Path("id") customerId: Int,
        @Path("favoriteShopId") favoriteShopId: Int,
    ): List<FavoriteShop>

    companion object {
        const val ROUTE = "${CustomersApi.ROUTE}/{id}/favoriteShops"
    }
}