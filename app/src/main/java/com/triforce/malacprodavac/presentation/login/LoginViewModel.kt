package com.triforce.malacprodavac.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.use_case.ValidateEmail
import com.triforce.malacprodavac.domain.use_case.ValidatePassword
import com.triforce.malacprodavac.domain.use_case.login.Login
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: Login,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val validateEmail: ValidateEmail = ValidateEmail()
    private val validatePassword: ValidatePassword = ValidatePassword()

    var state by mutableStateOf(LoginFormState())

    init {
        if (isUserAuthenticated())
            getMe()
    }

    fun isUserAuthenticated(): Boolean {
        return state.isSuccessful || sessionManager.getAccessToken().toBoolean()
    }

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        state = state.copy(emailError = emailResult.errorMessage)
        val passwordResult = validatePassword.execute(state.password)
        state = state.copy(emailError = passwordResult.errorMessage)
        Log.d("1", "1111")
        viewModelScope.launch {
            Log.d("2", "2222")
            loginUseCase.loginUser(state.email, state.password)
                .collect { result ->
                    Log.d("3", "3333")
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                isSuccessful = true
                            )
                        }

                        is Resource.Error -> {
                            Unit
                        }

                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                }
        }
    }

    private fun getMe() {
        viewModelScope.launch {
            loginUseCase.me().collect {
                when (it) {
                    is Resource.Success -> {
                        state = state.copy(
                            isSuccessful = true
                        )
                    }

                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = it.isLoading)
                    }
                }
            }
        }
    }

}
