package com.triforce.malacprodavac.data.remote.categories

import com.triforce.malacprodavac.data.remote.Api
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.Locale.Category

interface CategoriesApi {

    @GET(BASE_URL)
    suspend fun getAllCategories() : List<Category>

    @GET("${BASE_URL}/{id}")
    suspend fun getCategoryForId(
        @Path("id") categoryId: Int
    ): Category

    companion object{
        const val BASE_URL="${Api.BASE_URL}/categories"
    }
}