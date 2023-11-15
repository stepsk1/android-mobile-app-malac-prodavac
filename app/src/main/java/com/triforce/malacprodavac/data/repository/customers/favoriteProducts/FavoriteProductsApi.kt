package com.triforce.malacprodavac.data.repository.customers.favoriteProducts

import com.triforce.malacprodavac.data.remote.customers.CustomersApi
import com.triforce.malacprodavac.data.repository.customers.favoriteProducts.dto.CreateFavoriteProductDto
import com.triforce.malacprodavac.domain.model.PaginationResponse
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface FavoriteProductsApi {
    @POST(ROUTE)
    suspend fun createFavoriteProduct(
        @Path("id") customerId: Int,
        @Body() body: CreateFavoriteProductDto
    ): FavoriteProduct

    @GET(ROUTE)
    suspend fun getFavoriteProducts(
        @Path("id") customerId: Int,
        @QueryMap queryMap: MutableMap<String, String>
    ): PaginationResponse<FavoriteProduct>

    @DELETE("${ROUTE}/{favoriteProductId}")
    suspend fun deleteFavoriteProduct(
        @Path("id") customerId: Int,
        @Path("favoriteProductId") favoriteProductId: Int
    ): FavoriteProduct


    companion object {
        const val ROUTE: String = "${CustomersApi.ROUTE}/{id}/favoriteProducts"
    }
}