package com.triforce.malacprodavac.data.repository

import android.util.Log
import com.triforce.malacprodavac.data.local.MalacProdavacDatabase
import com.triforce.malacprodavac.data.mappers.toUser
import com.triforce.malacprodavac.data.mappers.toUserEntity
import com.triforce.malacprodavac.data.remote.UserApi
import com.triforce.malacprodavac.data.remote.dto.AuthenticationResponse
import com.triforce.malacprodavac.data.remote.dto.LoginRequest
import com.triforce.malacprodavac.data.remote.dto.RegisterRequest
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.repository.UserRepository
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val db: MalacProdavacDatabase,
    private val sessionManager: SessionManager
): UserRepository {
    private val dao = db.userDao

    override suspend fun registerUser(
        email: String,
        firstName: String,
        lastName: String,
//        profilePic: String,
        password: String,
        repeatPassword: String,
        role: String
    ): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val authResponse = try {
                if(role == "KUPAC"){
                    api.registerCustomer(
                        RegisterRequest(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            password = password,
                            repeatPassword = repeatPassword,
                            role = role
    //                        profilePic = ""
                        )
                    )
                }
                else if(role == "DOSTAVLJAČ"){
                    api.registerCouriers(
                        RegisterRequest(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            password = password,
                            repeatPassword = repeatPassword,
                            role = role
//                          profilePic = ""
                        )
                    )
                }
                else{
                    api.registerShops(
                        RegisterRequest(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            password = password,
                            repeatPassword = repeatPassword,
                            role = role
                            //                        profilePic = ""
                        )
                    )
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            }
            authResponse?.let {
                authenticateUser(it)
                dao.insertUser(listOf(it.user.toUserEntity()))
                emit(Resource.Success(data = it.user.toUser()))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun loginUser(email: String, password: String): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val authResponse = try {
                api.loginUser(
                    LoginRequest(email, password)
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't register user."))
                null
            } catch (e: HttpException) {
                if(e.code() == 401)
                    emit(Resource.Error(data=null, message = "Unauthenticated"))
                else
                    emit(Resource.Error(data=null, message = e.message()))
                null
            }
            authResponse?.let {
                authenticateUser(it)
                dao.insertUser(listOf(it.user.toUserEntity()))
                emit(Resource.Success(data = it.user.toUser()))
                Log.d("LOGGED_IN", sessionManager.getAccessToken()+" "+sessionManager.getCurrentEmail())
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    private fun authenticateUser(it: AuthenticationResponse) {
        sessionManager.refreshToken(it.authToken.value) /// do refresh logic
        sessionManager.setCurrentEmail(it.user.email)
        sessionManager.setCurrentUserId(it.user.userId)
    }

    override suspend fun getUser(id: Int): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val user = try {
                val email = sessionManager.getCurrentEmail()
                dao.getUserForEmail(email!!).first()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            user?.let{
                emit(Resource.Success(data = user.toUser()))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun getCurrentUser(): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val user = try {
                val email = sessionManager.getCurrentEmail()
                dao.getUserForEmail(email!!).first()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find user."))
                null
            }

            user?.let{
                emit(Resource.Success(data = user.toUser()))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }
}