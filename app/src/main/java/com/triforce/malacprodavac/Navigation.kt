package com.triforce.malacprodavac

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.triforce.malacprodavac.presentation.cart.CartDetails.CartDetailsScreen
import com.triforce.malacprodavac.presentation.cart.DetailsOrder.DetailsOrderScreen
import com.triforce.malacprodavac.presentation.cart.CartScreen
import com.triforce.malacprodavac.presentation.category.StoreCategoryScreen
import com.triforce.malacprodavac.presentation.higlightDetailed.HighlightDetailed
import com.triforce.malacprodavac.presentation.home.HomeScreen
import com.triforce.malacprodavac.presentation.home.shopHome.ShopHomeScreen
import com.triforce.malacprodavac.presentation.login.LoginScreen
import com.triforce.malacprodavac.presentation.product.ProductScreen
import com.triforce.malacprodavac.presentation.profile.ProfileScreen
import com.triforce.malacprodavac.presentation.registration.RegistrationScreen
import com.triforce.malacprodavac.presentation.store.StoreScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val startDestination = Screen.LoginScreen.route
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

        composable(
            route = Screen.StoreCategoryScreen.route + "?categoryId={categoryId}&title={title}",
            arguments = listOf(

                navArgument(
                    name = "categoryId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "title"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }

            )) {
            StoreCategoryScreen(navController = navController)
        }

        composable(route = Screen.CartScreen.route) {
            CartScreen(navController = navController)
        }

        composable(route = Screen.CartDetailsScreen.route) {
            CartDetailsScreen(navController = navController)
        }

        composable(route = Screen.DetailsOrderScreen.route) {
            DetailsOrderScreen(navController = navController)
        }

        composable(
            route = Screen.ProductScreen.route + "?productId={productId}",
            arguments = listOf(
                navArgument(
                    name = "productId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            ProductScreen(navController = navController)
        }

        composable(route = Screen.HighlightDetailed.route) {
            HighlightDetailed(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.ShopHomeScreen.route) {
            ShopHomeScreen(navController = navController)
        }


    }
}