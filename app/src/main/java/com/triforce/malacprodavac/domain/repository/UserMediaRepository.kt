package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.domain.model.UserMedia
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserMediaRepository {
    suspend fun getUserMedia(id: Int): Flow<Resource<UserMedia>>
}