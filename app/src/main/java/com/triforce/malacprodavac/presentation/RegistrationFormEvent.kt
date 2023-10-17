package com.triforce.malacprodavac.presentation

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String): RegistrationFormEvent()
    data class PasswordChanged(val password: String): RegistrationFormEvent()
    data class RepeatedPasswordChanged
        (val repeatedPassword: String): RegistrationFormEvent()
    data class AcceptTermsChanged(val isAccepted: String): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}
