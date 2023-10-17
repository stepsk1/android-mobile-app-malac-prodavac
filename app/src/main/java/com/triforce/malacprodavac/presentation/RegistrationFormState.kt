package com.triforce.malacprodavac.presentation

class RegistrationFormState {
    var email: String = ""
    val emailError: String? = null
    var password: String = ""
    val passwordError: String? = null
    var repeatedPassword: String = ""
    val repeatedPasswordError: String? = null
    var acceptedTerms: Boolean = false
    val termsError: String? = null

}