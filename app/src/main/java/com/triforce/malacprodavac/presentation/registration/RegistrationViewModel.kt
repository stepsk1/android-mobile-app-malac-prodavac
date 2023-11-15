package com.triforce.malacprodavac.presentation.registration

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.CreateCourier
import com.triforce.malacprodavac.domain.model.CreateCustomer
import com.triforce.malacprodavac.domain.model.CreateShop
import com.triforce.malacprodavac.domain.model.CreateUser
import com.triforce.malacprodavac.domain.use_case.registration.Registration
import com.triforce.malacprodavac.domain.use_case.validate.ValidateEmail
import com.triforce.malacprodavac.domain.use_case.validate.ValidateFirstName
import com.triforce.malacprodavac.domain.use_case.validate.ValidateLastName
import com.triforce.malacprodavac.domain.use_case.validate.ValidatePassword
import com.triforce.malacprodavac.domain.use_case.validate.ValidateRepeatedPassword
import com.triforce.malacprodavac.domain.use_case.validate.ValidateTerms
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.domain.util.enum.UserRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registration: Registration
) : ViewModel() {

    private val validateStringFirstName = ValidateFirstName()
    private val validateStringLastName = ValidateLastName()
    private val validateStringEmail = ValidateEmail()
    private val validateStringPassword = ValidatePassword()
    private val validateStringRepeatedPassword =
        ValidateRepeatedPassword()
    private val validateStringTerms = ValidateTerms()


    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    var hasError = false

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
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
        val firstNameResult = validateStringFirstName.execute(state.firstName)
        val lastNameResult = validateStringLastName.execute(state.lastName)
        val emailResult = validateStringEmail.execute(state.email)
        val passwordResult = validateStringPassword.execute(state.password)
        val repeatedPasswordResult = validateStringRepeatedPassword.execute(
            state.password, state.repeatedPassword
        )
        val termsResult = validateStringTerms.execute(state.acceptedTerms)

        val firstName = state.firstName
        val lastName = state.lastName
        val email = state.email
        val password = state.password
        val createUser = CreateUser(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )

        hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }


        if (!hasError) {
            registerUser(createUser, state.role)
        }

        if (hasError) {
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

    private fun registerUser(createUser: CreateUser, role: UserRole) {
        viewModelScope.launch {
            when (role) {
                UserRole.Customer -> registration.createCustomer(CreateCustomer(createUser))
                UserRole.Courier -> registration.createCourier(CreateCourier(createUser))
                UserRole.Shop -> registration.createShop(CreateShop(createUser))
            }.collect { result ->
                when (result) {
                    is Resource.Error -> {
                        state = state.copy(successful = false, emailError = result.message)
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        Log.d("DSF","SDFSDf")
                        state = state.copy(successful = true)
                    }
                }

            }
        }
    }


    private fun handleRegistrationError() {

    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}