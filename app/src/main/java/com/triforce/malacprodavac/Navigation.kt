package com.triforce.malacprodavac
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.triforce.malacprodavac.presentation.login.LoginScreen
import com.triforce.malacprodavac.presentation.registration.RegistrationScreen
import com.triforce.malacprodavac.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var startDestination = Screen.LoginScreen.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.RegistrationScreen.route + "",
            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                    defaultValue = "Uros"
//                    nullable = true
//                }
            )
        ) {
            RegistrationScreen(navController = navController)
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.StoreScreen.route) {
            StoreScreen(navController = navController)
        }

        composable(route = Screen.StoreCategoryScreen.route) {
            StoreCategoryScreen(navController = navController)
        }

        composable(route = Screen.CartScreen.route) {
            CartScreen(navController = navController)
        }

        composable(route = Screen.CartDetailsScreen.route) {
            CartDetailsScreen(navController = navController)
        }
    }
}