package com.triforce.malacprodavac.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.triforce.malacprodavac.ui.theme.MalacProdavacTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MalacProdavacTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    val viewModel = viewModel<MainViewModel>()
                    val state = viewModel.state
                    val context = LocalContext.current
                    LaunchedEffect(key1 = context) {
                        viewModel.validationEvents.collect { event ->
                            when(event) {
                                is MainViewModel.ValidationEvent.Success -> {
                                    Toast.makeText(
                                        context,
                                        "Registration successful",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = state.firstName,
                            onValueChange = {
                                viewModel.onEvent(RegistrationFormEvent.FirstNameChanged(it))
                            },
                            isError = state.firstNameError != null,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "First name")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                        if(state.firstNameError != null) {
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
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Last name")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                        if(state.lastNameError != null) {
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
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Email")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            )
                        )
                        if(state.emailError != null) {
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
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if(state.passwordError != null) {
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
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Repeated password")
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if(state.repeatedPasswordError != null) {
                            Text(
                                text = state.repeatedPasswordError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        DropDownList()

                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row (
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = state.acceptedTerms,
                                onCheckedChange = {
                                    viewModel.onEvent(RegistrationFormEvent.AcceptTermsChanged(it))
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Accept terms")
                        }
                        if(state.termsError != null) {
                            Text(
                                text = state.termsError,
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Button(onClick = {
                            viewModel.onEvent(RegistrationFormEvent.Submit)
                        },
                        modifier = Modifier.align(Alignment.End)) {
                            Text(text = "Submit")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownList() {
    val rolesList = listOf(
        "BUYER",
        "DELIVERER",
        "SELLER"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(rolesList[0]) }

    // menu box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
            readOnly = true,
            value = selectedRole,
            onValueChange = {},
            label = { Text("Roles") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },) {
            rolesList.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption)},
                    onClick = {
                        selectedRole = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}