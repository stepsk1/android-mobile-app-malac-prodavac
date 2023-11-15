package com.triforce.malacprodavac.domain.use_case.login

import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.repository.AuthRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class Me(private val repository: AuthRepository) {
    suspend operator fun invoke(): Flow<Resource<User>> {
        return repository.me()
    }
}