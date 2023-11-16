package com.triforce.malacprodavac.data.repository.auth


import android.util.Log
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
    private val sessionManager: SessionManager
) : AuthRepository {
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
                emit(Resource.Success(data = it))
            }
            emit(Resource.Loading(isLoading = false))
        }
    }

    override suspend fun me(): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val user = try {
                api.me()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't get user."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't get user."))
                null
            }
            user?.let {
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
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't get user."))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't get user."))
            }
            emit(Resource.Success(Unit))
            Log.d("COOKIE", sessionManager.getAccessToken() ?: "")
            emit(Resource.Loading(isLoading = false))
        }
    }

}