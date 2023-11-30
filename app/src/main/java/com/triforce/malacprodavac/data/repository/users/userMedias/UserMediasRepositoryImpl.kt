package com.triforce.malacprodavac.data.repository.users.userMedias

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.remote.users.userMedias.UserMediasApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.UserMedia
import com.triforce.malacprodavac.domain.repository.users.userMedias.UserMediasRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMediasRepositoryImpl @Inject constructor(
    private val api: UserMediasApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
) : UserMediasRepository {
    override suspend fun setUserProfilePicture(id: Int, file: File): Flow<Resource<UserMedia>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val part = file.asRequestBody("image/jpeg".toMediaType())

            val userMedia = try {
                api.putMedia(
                    id,
                    MultipartBody.Part.createFormData(
                        "image",
                        file.name,
                        part
                    )
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            userMedia?.let {
                emit(Resource.Success(data = userMedia))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun getUserMedias(id: Int): Flow<Resource<List<UserMedia>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val userMedias = try {
                api.getMedias(id, null)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            userMedias?.let {
                emit(Resource.Success(data = userMedias.data))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun deleteUserMedia(id: Int, mediaId: Int): Flow<Resource<UserMedia>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val userMedia = try {
                api.deleteMedia(id, mediaId)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            userMedia?.let {
                emit(Resource.Success(data = userMedia))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }
}