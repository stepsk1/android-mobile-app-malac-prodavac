package com.triforce.malacprodavac.presentation.login

import com.triforce.malacprodavac.presentation.RegistrationFormEvent

sealed class LoginFormEvent {
    data class EmailChanged(val email: String): LoginFormEvent()
    data class PasswordChanged(val password: String): LoginFormEvent()
    object Submit: LoginFormEvent()
}
