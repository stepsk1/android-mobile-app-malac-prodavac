package com.triforce.malacprodavac.data.repository.auth


import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.local.user.relations.UserWithRelations
import com.triforce.malacprodavac.data.mappers.users.toUser
import com.triforce.malacprodavac.data.mappers.users.toUserEntity
import com.triforce.malacprodavac.data.mappers.users.toUserWithRelations
import com.triforce.malacprodavac.data.remote.auth.AuthApi
import com.triforce.malacprodavac.data.remote.auth.dto.LoginDto
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.repository.AuthRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
) : AuthRepository {
    private val userDao = db.userDao
    private val userMediaDao = db.userMediaDao
    private val customerDao = db.customerDao
    private val courierDao = db.courierDao
    private val shopDao = db.shopDao

    override suspend fun login(email: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val user = try {
                api.login(
                    LoginDto(email, password)
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't login user."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                if (e.code() == HttpURLConnection.HTTP_NOT_FOUND)
                    emit(Resource.Error("Email adresa nije pronađena!"))
                else if (e.code() == HttpURLConnection.HTTP_UNAUTHORIZED)
                    emit(Resource.Error("Pogrešna lozinka!"))
                else
                    emit(Resource.Error("Kombinacija adrese i lozinke je neispravna!"))
                null
            }
            user?.let {
                insertUserWithRelations(it.toUserWithRelations())
                sessionManager.refreshAuthUserId(user.id)
                emit(Resource.Success(data = it))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun me(): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val authUserId = sessionManager.getAuthUserId()

            val user = if (authUserId > 0) {
                userDao.getUserWithRelations(authUserId).toUser()
            } else {
                try {
                    api.me().let {
                        userDao.insertUser(listOf(it.toUserEntity()))
                        return@let it
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error("Couldn't get user."))
                    null
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error("Couldn't get user."))
                    null
                }
            }

            user?.let {
                insertUserWithRelations(it.toUserWithRelations())
                sessionManager.refreshAuthUserId(it.id)
                emit(Resource.Success(data = it))
            }

            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun logout(): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            try {
                api.logout()
                sessionManager.logout()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't get user."))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't get user."))
            }

            emit(Resource.Loading(isLoading = false))
        }
    }

    private suspend fun insertUserWithRelations(user: UserWithRelations) {
        userDao.insertUser(listOf(user.user))
        user.profilePicture?.let { image ->
            userMediaDao.insertUserMedia(listOf(image))
        }
        user.customer?.let { customer ->
            customerDao.insertCustomers(listOf(customer))
        }
        user.courier?.let { courier ->
            courierDao.insertCourier(listOf(courier))
        }
        user.shop?.let { shop ->
            shopDao.insertShops(listOf(shop))
        }
    }

}