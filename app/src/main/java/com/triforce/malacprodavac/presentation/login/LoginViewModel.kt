package com.triforce.malacprodavac.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.use_case.login.Login
import com.triforce.malacprodavac.domain.use_case.validate.ValidateEmail
import com.triforce.malacprodavac.domain.use_case.validate.ValidatePassword
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
        getMe()
    }

    fun isUserAuthenticated(): Boolean {
        return sessionManager.isAuthenticated()
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
        state = state.copy(passwordError = passwordResult.errorMessage)

        val hasError = listOf(emailResult, passwordResult).any { !it.successful }

        if (!hasError)
            viewModelScope.launch {
                loginUseCase.loginUser(state.email, state.password)
                    .collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                state = state.copy(
                                    isSuccessful = true
                                )
                            }

                            is Resource.Error -> {
                                state =
                                    state.copy(isSuccessful = false, errorMessage = result.message)
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
                        state = state.copy(
                            isSuccessful = false
                        )
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = it.isLoading)
                    }
                }
            }
        }
    }

}
