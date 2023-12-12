package com.triforce.malacprodavac.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.SpaceLarge
import com.triforce.malacprodavac.ui.theme.SpaceMedium

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {}
    )

    if (viewModel.isUserAuthenticated()) {
        LaunchedEffect(Unit) {
            cartViewModel.clearCart()
            navController.navigate(Screen.HomeScreen.route)
        }
    }


    val scaffoldState = rememberScaffoldState()

    val annotatedString = buildAnnotatedString {
        val text = "Nemaš nalog? Registruj se!"
        append(text)

        val start = text.indexOf("Registruj se!")
        val end = start + "Registruj se!".length
        addStyle(
            SpanStyle(
                color = MP_Green, textDecoration = TextDecoration.Underline
            ), start, end
        )
        addStringAnnotation(
            "registration", "Screen.LoginScreen", start, end
        )
    }

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
            .padding(
                start = SpaceLarge, end = SpaceLarge, top = SpaceLarge, bottom = SpaceLarge
            )
            .verticalScroll(rememberScrollState())
    ) {
        PullRefreshIndicator(
            refreshing = state.isLoading, state = pullRefreshState, modifier = Modifier.align(
                Alignment.TopCenter
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(LoginFormEvent.EmailChanged(it))
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
                    color = MP_Pink,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
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
                    color = MP_Pink,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (!state.errorMessage.isNullOrBlank()) {
                Text(
                    text = state.errorMessage.toString(),
                    color = MP_Pink,
                    modifier = Modifier.align(Alignment.End)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.onEvent(LoginFormEvent.Submit)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Text(text = "Prijavi se")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ClickableText(text = annotatedString, onClick = {
                    navController.navigate(Screen.RegistrationScreen.route)
                })
            }
        }
    }
}