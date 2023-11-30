package com.triforce.malacprodavac.data.repository.users

import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.remote.users.UsersApi
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.repository.users.UserRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UsersApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
) : UserRepository {
    private val dao = db.userDao

    override suspend fun getUser(id: Int): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val user = try {
                api.getUser(id)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            user?.let {
                emit(Resource.Success(data = user))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }


}


