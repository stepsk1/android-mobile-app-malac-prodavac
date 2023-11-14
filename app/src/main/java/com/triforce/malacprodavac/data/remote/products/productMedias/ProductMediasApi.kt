package com.triforce.malacprodavac.data.remote.products.productMedias

import com.triforce.malacprodavac.data.local.product.productMedia.ProductMediaEntity
import com.triforce.malacprodavac.data.remote.products.ProductsApi
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ProductMediasApi {
    @Multipart
    @POST(ROUTE)
    suspend fun create(@Path("id") id: Int, @Part("images") images: MultipartBody.Part): Int

    @GET(ROUTE)
    suspend fun getProductMedias(@Path("id") id: Int): List<ProductMediaEntity>

    @GET("${ROUTE}/{mediaId}")
    suspend fun getProductMedias(
        @Path("id") id: Int,
        @Path("mediaId") mediaId: Int
    ): ProductMediaEntity


    companion object {
        const val ROUTE = "${ProductsApi.ROUTE}/{id}/medias"
    }
}