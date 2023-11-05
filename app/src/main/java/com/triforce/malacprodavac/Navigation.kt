package com.triforce.malacprodavac
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.triforce.malacprodavac.presentation.login.LoginScreen
import com.triforce.malacprodavac.presentation.RegistrationScreen
import com.triforce.malacprodavac.presentation.cart.CartScreen
import com.triforce.malacprodavac.presentation.cart.CartDetails.CartDetailsScreen
import com.triforce.malacprodavac.presentation.home.HomeScreen
import com.triforce.malacprodavac.presentation.store.StoreScreen
import com.triforce.malacprodavac.presentation.store.category.StoreCategoryScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.RegistrationScreen.route + "",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Uros"
                    nullable = true
                }
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