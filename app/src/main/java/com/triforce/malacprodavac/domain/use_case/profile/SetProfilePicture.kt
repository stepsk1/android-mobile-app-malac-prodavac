package com.triforce.malacprodavac.domain.use_case.profile

import com.triforce.malacprodavac.domain.model.UserMedia
import com.triforce.malacprodavac.domain.repository.users.userMedias.UserMediasRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

data class SetProfilePicture(val repository: UserMediasRepository) {
    suspend operator fun invoke(id: Int, file: File): Flow<Resource<UserMedia>> {
        return repository.setUserProfilePicture(id, file)
    }
}