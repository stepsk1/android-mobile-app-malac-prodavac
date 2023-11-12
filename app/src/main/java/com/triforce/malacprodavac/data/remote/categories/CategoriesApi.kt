package com.triforce.malacprodavac.data.remote.categories

import com.triforce.malacprodavac.data.local.category.CategoryEntity
import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CategoriesApi {

    @GET(ROUTE)
    suspend fun getAllCategories(
        @Query("limit") limit : Int = 80,
        @QueryMap() queryMap: Map<String,  String>
    ): PaginationResponse<CategoryEntity>

    @GET("${ROUTE}/{id}")
    suspend fun getCategoryForId(
        @Path("id") categoryId: Int
    ): CategoryEntity
    

    companion object {
        const val ROUTE = "/categories"
    }
}