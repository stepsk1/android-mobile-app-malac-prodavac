package com.triforce.malacprodavac.domain.repository.users

import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(id: Int): Flow<Resource<User>>

}
