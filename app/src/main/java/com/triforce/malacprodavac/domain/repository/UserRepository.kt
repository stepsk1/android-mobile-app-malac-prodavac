package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun registerUser(
        email: String,
        firstName: String,
        lastName: String,
        //profilePic: String,
        password: String,
        repeatPassword: String,
        role: String
    ): Flow<Resource<User>>

    suspend fun loginUser(
        email: String,
        password: String
    ): Flow<Resource<User>>

    suspend fun getUser(id: Int): Flow<Resource<User>>

    suspend fun getCurrentUser(): Flow<Resource<User>>
}
