package com.triforce.malacprodavac.presentation.login
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.MainViewModel
import com.triforce.malacprodavac.presentation.RegistrationFormEvent
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val viewModel = viewModel<LoginViewModel>()
    val state = viewModel.state
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        val text = "Nemaš nalog? Registruj se!"
        append(text)

        val start = text.indexOf("Registruj se!")
        val end = start + "Registruj se!".length
        addStyle(
            SpanStyle(
                color = MP_Green,
                textDecoration = TextDecoration.Underline
            ),
            start,
            end
        )
        addStringAnnotation(
            "registration",
            "Screen.LoginScreen",
            start,
            end
        )
    }
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect {event ->
            when(event) {
                is LoginViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Uspešna prijava",
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
        OutlinedTextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.EmailChanged(it))
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
                color = MP_Pink,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Lozinka")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if(state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MP_Pink,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.onEvent(LoginFormEvent.Submit)
            if(!viewModel.hasError)
                navController.navigate(Screen.HomeScreen.route)
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "prijavi se")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            //val uriHandler = LocalUriHandler.current
            ClickableText(
                text = annotatedString,
                onClick = {offset ->
                    val uri = annotatedString.getStringAnnotations("registration",
                        offset, offset).firstOrNull()?.item
                    if(uri!=null)
                        navController.navigate(Screen.RegistrationScreen.route)
                }
            )
        }
    }
}