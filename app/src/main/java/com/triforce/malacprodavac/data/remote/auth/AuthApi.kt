package com.triforce.malacprodavac.data.remote.auth

import com.triforce.malacprodavac.data.remote.auth.dto.LoginDto
import com.triforce.malacprodavac.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("${ROUTE}/login")
    suspend fun login(
        @Body loginRequest: LoginDto
    ): User

    @GET("${ROUTE}/me")
    suspend fun me(): User

    @POST("${ROUTE}/logout")
    suspend fun logout(): Response<Unit>

    companion object {
        const val ROUTE = "/auth"
    }
}