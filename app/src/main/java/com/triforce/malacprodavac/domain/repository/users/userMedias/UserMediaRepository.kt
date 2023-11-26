package com.triforce.malacprodavac.domain.repository.users.userMedias

import android.net.Uri
import com.triforce.malacprodavac.domain.model.UserMedia
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserMediasRepository {

    suspend fun setUserProfilePicture(id: Int, file: File): Flow<Resource<UserMedia>>
    suspend fun getUserMedias(id: Int): Flow<Resource<List<UserMedia>>>

    suspend fun deleteUserMedia(id: Int, mediaId: Int): Flow<Resource<UserMedia>>
}