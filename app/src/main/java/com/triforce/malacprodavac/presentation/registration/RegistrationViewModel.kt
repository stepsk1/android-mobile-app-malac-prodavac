package com.triforce.malacprodavac.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.repository.UserRepository
import com.triforce.malacprodavac.domain.use_case.ValidateEmail
import com.triforce.malacprodavac.domain.use_case.ValidateFirstName
import com.triforce.malacprodavac.domain.use_case.ValidateLastName
import com.triforce.malacprodavac.domain.use_case.ValidatePassword
import com.triforce.malacprodavac.domain.use_case.ValidateRepeatedPassword
import com.triforce.malacprodavac.domain.use_case.ValidateTerms
import com.triforce.malacprodavac.util.AuthResult
import com.triforce.malacprodavac.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    //private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel() {

    private val validateFirstName: ValidateFirstName = ValidateFirstName()
    private val validateLastName: ValidateLastName = ValidateLastName()
    private val validateEmail: ValidateEmail = ValidateEmail()
    private val validatePassword: ValidatePassword = ValidatePassword()
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword()
    private val validateTerms: ValidateTerms = ValidateTerms()
    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    var hasError = false

    fun onEvent(event: RegistrationFormEvent) {
        when(event) {
            is RegistrationFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is RegistrationFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is RegistrationFormEvent.RoleChanged -> {
                state = state.copy(role = event.role)
            }
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.AcceptTermsChanged -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)
        val firstName = state.firstName
        val lastName = state.lastName
        val email = state.email
        val password = state.password
        val user = User(
            4,
             firstName,
             lastName,
             email,
             password,
            "",
            0,
            0,
            null,
            "RSD",
            "onDelivery",
            "",
            listOf<String>("KUPAC"),
            null,
            null,
            null,
            LocalDateTime.now()
        )

        hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }


        if(!hasError) {
            registerCustomer(user)
        }

        if(hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            state = state.copy(
                firstNameError = "",
                lastNameError = "",
                emailError = "",
                passwordError = "",
                repeatedPasswordError = "",
                termsError = ""
            )
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private fun registerCustomer(user: User) {
        viewModelScope.launch {
            repository.registerCustomer(user)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            if (result.data !is Customer) {
                                state = state.copy(
                                    status = AuthResult.Unauthorized()
                                )
                            }
                            if (result.data is Customer) {
                                state = state.copy(
                                    status = AuthResult.Authorized(state.email)
                                )
                            }
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

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}