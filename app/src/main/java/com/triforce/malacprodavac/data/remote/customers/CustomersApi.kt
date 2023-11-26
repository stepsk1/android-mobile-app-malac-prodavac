package com.triforce.malacprodavac.data.remote.customers


import com.triforce.malacprodavac.data.remote.customers.dto.CreateCustomerDto
import com.triforce.malacprodavac.data.remote.customers.dto.CreateFavoriteProductDto
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.PaginationResponse
import com.triforce.malacprodavac.domain.model.SchedulePickup
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface CustomersApi {
    @POST(ROUTE)
    suspend fun registerCustomer(
        @Body registerRequest: CreateCustomerDto
    ): Customer

    @GET(ROUTE)
    suspend fun getCustomers(@QueryMap queryMap: MutableMap<String, String>): PaginationResponse<Customer>

    @GET("${ROUTE}/{id}")
    suspend fun getCustomer(@Path("id") customerId: Int): Customer

    @PATCH("${ROUTE}/{id}")
    suspend fun updateCustomer(
        @Path("id") customerId: Int,
        @Body updateBody: CreateCustomerDto
    ): Customer

    @POST("${ROUTE}/{id}/favoriteProducts")
    suspend fun createFavoriteProduct(
        @Path("id") customerId: Int,
        @Body() body: CreateFavoriteProductDto
    ): FavoriteProduct

    @GET("${ROUTE}/{id}/favoriteProducts")
    suspend fun getFavoriteProducts(
        @Path("id") customerId: Int,
        //@QueryMap queryMap: MutableMap<String, String>
    ): PaginationResponse<FavoriteProduct>

    @DELETE("${ROUTE}/{id}/favoriteProducts/favoriteProductId/{favoriteProductId}")
    suspend fun deleteFavoriteProduct(
        @Path("id") customerId: Int,
        @Path("favoriteProductId") favoriteProductId: Int
    ): FavoriteProduct

    @GET("${ROUTE}/{id}/scheduledPickups")
    suspend fun getCustomerSchedulePickups(
        @Path("id") customerId: Int,
        //@QueryMap queryMap: MutableMap<String, String>
    ): PaginationResponse<SchedulePickup>

    @GET("${ROUTE}/{id}/scheduledPickups/{scheduledPickupId}")
    suspend fun getCustomerSchedulePickupsById(
        @Path("id") customerId: Int,
        @Path("scheduledPickupId") scheduledPickupId: Int
        //@QueryMap queryMap: MutableMap<String, String>
    ): SchedulePickup

    companion object {
        const val ROUTE = "/customers"
    }
}