package com.triforce.malacprodavac.presentation

data class RegistrationFormState (
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val role: Role = Role.BUYER,
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)