package com.triforce.malacprodavac.domain.use_case.profile

import com.triforce.malacprodavac.domain.repository.AuthRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

data class Logout(val repository: AuthRepository) {
    suspend operator fun invoke(): Flow<Resource<Unit>> {
        return repository.logout()
    }
}