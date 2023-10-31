package com.triforce.malacprodavac.data.remote

import com.triforce.malacprodavac.data.remote.dto.AuthenticationResponse
import com.triforce.malacprodavac.data.remote.dto.LoginRequest
import com.triforce.malacprodavac.data.remote.dto.RegisterCourierRequest
import com.triforce.malacprodavac.data.remote.dto.RegisterCustomerRequest
import com.triforce.malacprodavac.data.remote.dto.RegisterShopRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST(REGISTER_CUSTOMER)
    suspend fun registerCustomer(
        @Body registerRequest: RegisterCustomerRequest
    ): AuthenticationResponse

    @POST(REGISTER_COURIER)
    suspend fun registerCouriers(
        @Body registerRequest: RegisterCourierRequest
    ): AuthenticationResponse

    @POST(REGISTER_SHOP)
    suspend fun registerShops(
        @Body registerRequest: RegisterShopRequest
    ): AuthenticationResponse
    @POST(LOGIN)
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): AuthenticationResponse

    companion object {
        const val BASE_URL = "http://localhost:3000/"
        const val LOGIN = "auth/login/"
        const val REGISTER_CUSTOMER = "customers/"
        const val REGISTER_COURIER = "couriers/"
        const val REGISTER_SHOP = "shops/"
    }
}