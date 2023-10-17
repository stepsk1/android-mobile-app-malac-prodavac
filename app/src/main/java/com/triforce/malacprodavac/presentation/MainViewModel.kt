package com.triforce.malacprodavac.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.use_case.ValidateEmail
import com.triforce.malacprodavac.domain.use_case.ValidatePassword
import com.triforce.malacprodavac.domain.use_case.ValidateRepeatedPassword
import com.triforce.malacprodavac.domain.use_case.ValidateTerms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
): ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when(event) {
            is RegistrationFormEvent.EmailChanged -> {
                state.email = event.email
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state.password = event.password
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state.repeatedPassword = event.repeatedPassword
            }
            is RegistrationFormEvent.AcceptTermsChanged -> {
                //state.acceptedTerms = event.isAccepted
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

//        if(hasError) {
//            state = state(
//                emailError = emailResult.errorMessage,
//                passwordError = passwordResult.errorMessage,
//                repeatedPasswordError = repeatedPasswordResult.errorMessage,
//                termsError = termsResult.errorMessage
//            )
//            return
//        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}