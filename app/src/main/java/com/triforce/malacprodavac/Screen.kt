package com.triforce.malacprodavac

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegistrationScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object StoreScreen : Screen("store_screen")
    object StoreCategoryScreen : Screen("store_category_screen")
    object ProductScreen : Screen("product_screen")
    object CartScreen : Screen("cart_screen")
    object  CartDetailsScreen : Screen("cart_deatils_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
