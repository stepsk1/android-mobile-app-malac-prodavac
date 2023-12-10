package com.triforce.malacprodavac.data.remote.categories

import com.triforce.malacprodavac.data.remote.dto.PaginationResponse
import com.triforce.malacprodavac.domain.model.Category
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CategoriesApi {

    @GET(ROUTE)
    suspend fun getAllCategories(
        @Query("limit") limit: Int = 80,
        @QueryMap() queryMap: Map<String, String> = emptyMap()
    ): PaginationResponse<Category>

    @GET("${ROUTE}/{id}")
    suspend fun getCategory(
        @Path("id") categoryId: Int
    ): Category


    companion object {
        const val ROUTE = "/categories"
    }
}