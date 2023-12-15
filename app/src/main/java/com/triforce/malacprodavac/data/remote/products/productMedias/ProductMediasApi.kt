package com.triforce.malacprodavac.data.remote.products.productMedias

import com.triforce.malacprodavac.data.remote.products.ProductsApi
import com.triforce.malacprodavac.domain.model.pagination.PaginationResult
import com.triforce.malacprodavac.domain.model.products.productMedias.ProductMedia
import okhttp3.MultipartBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ProductMediasApi {
    @Multipart
    @PUT(ROUTE)
    suspend fun create(@Path("id") id: Int, @Part() image: MultipartBody.Part): ProductMedia

    @GET(ROUTE)
    suspend fun getProductMedias(@Path("id") productId: Int): PaginationResult<ProductMedia>

    @DELETE("${ROUTE}/{mediaId}")
    suspend fun deleteProductMedia(
        @Path("id") productId: Int,
        @Path("mediaId") mediaId: Int
    ): ProductMedia


    companion object {
        const val ROUTE = "${ProductsApi.ROUTE}/{id}/medias"
    }
}