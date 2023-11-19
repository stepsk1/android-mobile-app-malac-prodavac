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
    object DetailsOrderScreen : Screen("details_order_screen")
    object PrivateProfile : Screen("private_profile_screen")
    object PublicProfile : Screen("public_profile_screen")
    object ShopHomeScreen : Screen("shop_home_screen")
    object HighlightSection : Screen("highlight_section")
    object OrderScreen : Screen("order_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
