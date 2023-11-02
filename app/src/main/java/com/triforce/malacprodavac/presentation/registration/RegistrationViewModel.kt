package com.triforce.malacprodavac.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.CreateUser
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.domain.repository.UserRepository
import com.triforce.malacprodavac.domain.use_case.ValiStringEmail
import com.triforce.malacprodavac.domain.use_case.ValiStringFirstName
import com.triforce.malacprodavac.domain.use_case.ValiStringLastName
import com.triforce.malacprodavac.domain.use_case.ValiStringPassword
import com.triforce.malacprodavac.domain.use_case.ValiStringRepeatedPassword
import com.triforce.malacprodavac.domain.use_case.ValiStringTerms
import com.triforce.malacprodavac.util.AuthResult
import com.triforce.malacprodavac.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    //private val savedStateHandle: SavedStateHandle,
    private val repository: UserRepository
): ViewModel() {

    private val valiStringFirstName: ValiStringFirstName = ValiStringFirstName()
    private val valiStringLastName: ValiStringLastName = ValiStringLastName()
    private val valiStringEmail: ValiStringEmail = ValiStringEmail()
    private val valiStringPassword: ValiStringPassword = ValiStringPassword()
    private val valiStringRepeatedPassword: ValiStringRepeatedPassword = ValiStringRepeatedPassword()
    private val valiStringTerms: ValiStringTerms = ValiStringTerms()
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
        val firstNameResult = valiStringFirstName.execute(state.firstName)
        val lastNameResult = valiStringLastName.execute(state.lastName)
        val emailResult = valiStringEmail.execute(state.email)
        val passwordResult = valiStringPassword.execute(state.password)
        val repeatedPasswordResult = valiStringRepeatedPassword.execute(
            state.password, state.repeatedPassword)
        val termsResult = valiStringTerms.execute(state.acceptedTerms)
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


        if(!hasError) {
            if(state.role == "KUPAC")
                registerCustomer(createUser)
            else if(state.role == "DOSTAVLJAČ")
                registerCourier(createUser, 0.0)
            else
                registerShop(createUser, "")
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

    private fun registerCustomer(createUser: CreateUser) {
        viewModelScope.launch {
            repository.registerCustomer(createUser)
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

    private fun registerCourier(createUser: CreateUser, pricePerKilometer: Double) {
        viewModelScope.launch {
            repository.registerCourier(createUser, pricePerKilometer)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            if (result.data !is Courier) {
                                state = state.copy(
                                    status = AuthResult.Unauthorized()
                                )
                            }
                            if (result.data is Courier) {
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

    private fun registerShop(createUser: CreateUser, businessName: String) {
        viewModelScope.launch {
            repository.registerShop(createUser, businessName)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            if (result.data !is Shop) {
                                state = state.copy(
                                    status = AuthResult.Unauthorized()
                                )
                            }
                            if (result.data is Shop) {
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