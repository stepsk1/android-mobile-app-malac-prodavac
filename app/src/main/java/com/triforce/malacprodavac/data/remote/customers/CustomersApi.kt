package com.triforce.malacprodavac.data.remote.customers


import com.triforce.malacprodavac.data.remote.customers.dto.CreateCustomerDto
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.PaginationResponse
import retrofit2.http.Body
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

    companion object {
        const val ROUTE = "/customers"
    }
}