package com.triforce.malacprodavac.data.remote.products

import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import com.triforce.malacprodavac.data.remote.products.dto.CreateProductDto
import com.triforce.malacprodavac.data.remote.products.dto.UpdateProductDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProductsApi {
    @POST(ROUTE)
    suspend fun create(@Body() createPostDto: CreateProductDto): ProductEntity

    @GET(ROUTE)
    suspend fun getProducts(@QueryMap() queryMap: MutableMap<String, String>): PaginationResponse<ProductEntity>

    @GET("${ROUTE}/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductEntity

    @PATCH("${ROUTE}/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body() updateProductDto: UpdateProductDto
    ): ProductEntity

    @DELETE("${ROUTE}/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): ProductEntity

    companion object {
        const val ROUTE = "/products"
    }

}