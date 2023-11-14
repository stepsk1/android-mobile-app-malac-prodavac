package com.triforce.malacprodavac.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.ui.theme.SpaceLarge
import com.triforce.malacprodavac.ui.theme.SpaceMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel = hiltViewModel()

    val state = viewModel.state

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading
    )

    val context = LocalContext.current

    val annotatedString = buildAnnotatedString {
        val text = "Nemaš nalog? Registruj se!"
        append(text)

        val start = text.indexOf("Registruj se!")
        val end = start + "Registruj se!".length
        addStyle(
            SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MP_White
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
        viewModel.validationEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Uspešna prijava",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("", "ULOGA")
                    Log.d("", viewModel.role)
                    if (viewModel.role == "Shop")
                        navController.navigate(Screen.ShopHomeScreen.route)
                    else if(viewModel.role == "Customer")
                        navController.navigate(Screen.StoreScreen.route)

                }
            }
        }
    }

    SwipeRefresh(state = swipeRefreshState, onRefresh = {}) {
        LinearGradient(color1 = MP_Green, color2 = MP_GreenDark)
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
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(LoginFormEvent.EmailChanged(it))
                    },
                    isError = state.emailError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(35.dp)),
                    placeholder = {
                        Text(
                            text = "Email adresa",
                            color = MP_White
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )

                if (state.emailError != null) {
                    Text(
                        text = state.emailError,
                        color = MP_White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp),
                        style = MaterialTheme.typography.body2
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    textStyle = MaterialTheme.typography.body2,
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                    },
                    isError = state.passwordError != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(35.dp)),
                    placeholder = {
                        Text(
                            text = "Lozinka",
                            color = MP_White
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                if (state.passwordError != null) {
                    Text(
                        text = state.passwordError,
                        color = MP_White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp),
                        style = MaterialTheme.typography.body2,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.onEvent(LoginFormEvent.Submit)
                            .let {
                                navController.navigate(Screen.HomeScreen.route)
                            }
                    },
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = MP_White,
                        contentColor = MP_Black
                    ),

                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)


                ) {
                    Text(text = "Prijavi se")
                }

                Spacer(modifier = Modifier.height(16.dp))

                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        val uri = annotatedString.getStringAnnotations(
                            "registration",
                            offset, offset
                        ).firstOrNull()?.item
                        if (uri != null)
                            navController.navigate(Screen.RegistrationScreen.route)
                    }
                )

            }
        }
    }
}