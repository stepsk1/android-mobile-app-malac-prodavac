package com.triforce.malacprodavac.presentation.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.util.enum.UserRole
import com.triforce.malacprodavac.presentation.registration.components.DropDownList
import com.triforce.malacprodavac.ui.theme.SpaceLarge
import com.triforce.malacprodavac.ui.theme.SpaceMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading
    )
    val scaffoldState = rememberScaffoldState()

    val annotatedString = buildAnnotatedString {
        val text = "Imaš nalog? Prijavi se!"
        append(text)

        val start = text.indexOf("Prijavi se!")
        val end = start + "Prijavi se!".length
        addStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            start,
            end
        )
        addStringAnnotation(
            "login",
            "Screen.LoginScreen",
            start,
            end
        )
    }

    if (state.successful) {
        LaunchedEffect(true) {
            navController.navigate(Screen.LoginScreen.route)
        }
    }

    SwipeRefresh(state = swipeRefreshState, onRefresh = {}) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = SpaceLarge,
                    end = SpaceLarge,
                    top = SpaceLarge,
                    bottom = SpaceLarge
                )
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceMedium)
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                TextField(
                    value = state.firstName,
                    onValueChange = {
                        viewModel.onEvent(RegistrationFormEvent.FirstNameChanged(it))
                    },
                    isError = state.firstNameError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    placeholder = {
                        Text(text = "Ime")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                if (state.firstNameError != null) {
                    Text(
                        text = state.firstNameError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = state.lastName,
                    onValueChange = {
                        viewModel.onEvent(RegistrationFormEvent.LastNameChanged(it))
                    },
                    isError = state.lastNameError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    placeholder = {
                        Text(text = "Prezime")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
                if (state.lastNameError != null) {
                    Text(
                        text = state.lastNameError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
                    },
                    isError = state.emailError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    placeholder = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
                if (state.emailError != null) {
                    Text(
                        text = state.emailError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
                    },
                    isError = state.passwordError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    placeholder = {
                        Text(text = "Lozinka")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                if (state.passwordError != null) {
                    Text(
                        text = state.passwordError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = state.repeatedPassword,
                    onValueChange = {
                        viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
                    },
                    isError = state.repeatedPasswordError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    placeholder = {
                        Text(text = "Ponovljena lozinka")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                if (state.repeatedPasswordError != null) {
                    Text(
                        text = state.repeatedPasswordError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                DropDownList(
                    entries = enumValues<UserRole>().toList(),
                    handleSelect = { nzm ->
                        viewModel.onEvent(RegistrationFormEvent.RoleChanged(nzm as UserRole))
                    })
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(
                        checked = state.acceptedTerms,
                        onCheckedChange = {
                            viewModel.onEvent(RegistrationFormEvent.AcceptTermsChanged(it))
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Prihvati uslove")
                }
                if (state.termsError != null) {
                    Text(
                        text = state.termsError,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Button(
                    onClick = {
                        viewModel.onEvent(RegistrationFormEvent.Submit)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Text(text = "Registruj se")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ClickableText(
                        text = annotatedString,
                        onClick = {
                            navController.navigate(Screen.LoginScreen.route)
                        }
                    )
                }
            }
        }
    }
}
