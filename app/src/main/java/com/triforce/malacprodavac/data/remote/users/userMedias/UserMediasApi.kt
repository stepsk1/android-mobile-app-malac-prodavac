package com.triforce.malacprodavac.data.remote.users.userMedias

import com.triforce.malacprodavac.data.remote.users.UsersApi
import com.triforce.malacprodavac.domain.model.UserMedia
import okhttp3.MultipartBody
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface UserMediasApi {

    @Multipart
    @PUT(ROUTE)
    suspend fun putMedia(@Path("id") userId: Int, @Part() image: MultipartBody.Part): UserMedia

    @GET(ROUTE)
    suspend fun getMedias(
        @Path("id") userId: Int,
        @QueryMap queryMap: MutableMap<String, String>
    ): List<UserMedia>


    @DELETE("${ROUTE}/{mediaId}")
    suspend fun deleteMedia(
        @Path("id") userId: Int,
        @Path("mediaId") mediaId: Int
    ): UserMedia


    companion object {
        const val ROUTE = "${UsersApi.ROUTE}/{id}/medias"
    }
}