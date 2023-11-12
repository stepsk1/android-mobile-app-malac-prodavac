package com.triforce.malacprodavac.data.remote.products

import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ProductsApi {

    @GET(ROUTE)
    suspend fun getProducts(@QueryMap() queryMap: MutableMap<String, String>): PaginationResponse<ProductEntity>

    @GET("${ROUTE}/{id}")
    suspend fun getProductForId(@Path("id") productId: Int): ProductEntity

    companion object {
        const val ROUTE = "/products"
    }

}