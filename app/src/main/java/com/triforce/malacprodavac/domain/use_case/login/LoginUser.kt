package com.triforce.malacprodavac.domain.use_case.login

import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.repository.AuthRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class LoginUser(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<User>> {
        return repository.login(email, password)
    }
}