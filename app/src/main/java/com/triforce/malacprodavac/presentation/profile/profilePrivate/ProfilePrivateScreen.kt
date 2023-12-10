package com.triforce.malacprodavac.presentation.profile.profilePrivate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.User

@Composable
fun ProfilePrivateScreen(
    navController: NavController,
    viewModel: ProfilePrivateViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        if (viewModel.isLoggedIn() == true) {
            viewModel.me()
        } else {
            navController.navigate(Screen.LoginScreen.route)
        }
    }

    val user = state.currentUser

    LaunchedEffect(user) {
        if (user != null) {
            handleUserNavigation(navController, user)
        }
    }
}

private fun handleUserNavigation(navController: NavController, user: User?) {
    if (user != null) {
        val roles = user.roles

        when {
            roles.contains("Shop") -> navController.navigate(Screen.ShopPrivateScreen.route)
            roles.contains("Courier") -> navController.navigate(Screen.CourierPrivateScreen.route)
            roles.contains("Customer") -> navController.navigate(Screen.CustomerPrivateScreen.route)
            else -> navController.navigate(Screen.HomeScreen.route)
        }
    } else {
        navController.navigate(Screen.HomeScreen.route)
    }
}

