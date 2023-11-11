package com.triforce.malacprodavac.data.remote.products

import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET(ROUTE)
    suspend fun getProducts(): PaginationResponse

    @GET("${ROUTE}/{id}")
    suspend fun getProductForId(@Path("id") productId: Int): ProductEntity

    @GET(ROUTE)
    suspend fun getProductsForCategoryId(
        @Query("filter[0][field]") field: String,
        @Query("filter[0][type]") type: String,
        @Query("filter[0][value]") value: Int
    ): List<ProductEntity>

    companion object {
        const val ROUTE = "/products"
    }

}