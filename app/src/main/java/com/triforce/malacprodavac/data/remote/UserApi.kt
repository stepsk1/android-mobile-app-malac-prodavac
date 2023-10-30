package com.triforce.malacprodavac.data.remote

import com.triforce.malacprodavac.data.remote.dto.AuthenticationResponse
import com.triforce.malacprodavac.data.remote.dto.LoginRequest
import com.triforce.malacprodavac.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST(REGISTER_CUSTOMER)
    suspend fun registerCustomer(
        @Body registerRequest: RegisterRequest
    ): AuthenticationResponse

    @POST(REGISTER_COURIER)
    suspend fun registerCouriers(
        @Body registerRequest: RegisterRequest
    ): AuthenticationResponse

    @POST(REGISTER_SHOP)
    suspend fun registerShops(
        @Body registerRequest: RegisterRequest
    ): AuthenticationResponse
    @POST(LOGIN)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): AuthenticationResponse

    companion object {
        const val LOGIN = "http://localhost:3000/auth/login/"
        const val REGISTER_CUSTOMER = "http://localhost:3000/customers/"
        const val REGISTER_COURIER = "http://localhost:3000/couriers/"
        const val REGISTER_SHOP = "http://localhost:3000/shops/"
    }
}