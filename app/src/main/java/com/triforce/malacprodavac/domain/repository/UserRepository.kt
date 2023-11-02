package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.CreateUser
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerCustomer(
        createUser: CreateUser
    ): Flow<Resource<Customer>>

    suspend fun registerCourier(
        createUser: CreateUser,
        pricePerKilometer: Double
    ): Flow<Resource<Courier>>

    suspend fun registerShop(
        createUser: CreateUser,
        businessName: String
    ): Flow<Resource<Shop>>

    suspend fun loginUser(
        email: String,
        password: String
    ): Flow<Resource<User>>

    suspend fun getUser(id: Int): Flow<Resource<User>>

    suspend fun getCurrentUser(): Flow<Resource<User>>
}
