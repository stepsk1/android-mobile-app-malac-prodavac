package com.triforce.malacprodavac.data.remote.users

import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.domain.model.User
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface UsersApi {
    @GET(ROUTE)
    suspend fun getUsers(@QueryMap queryMap: MutableMap<String, String>): List<User>

    @GET("${ROUTE}/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @DELETE("${ROUTE}/{id}")
    suspend fun deleteUser(@Query("id") id: Int): User


    companion object {
        const val ROUTE = "/users"
    }
}