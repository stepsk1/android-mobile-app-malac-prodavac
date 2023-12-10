package com.triforce.malacprodavac

sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login_screen")
    data object RegistrationScreen : Screen("register_screen")
    data object HomeScreen : Screen("home_screen")
    data object StoreScreen : Screen("store_screen")
    data object StoreCategoryScreen : Screen("store_category_screen")
    data object ProductScreen : Screen("product_screen")
    data object CartScreen : Screen("cart_screen")
    data object CartDetailsScreen : Screen("cart_deatils_screen")
    data object DetailsOrderScreen : Screen("details_order_screen")
    data object ShopHomeScreen : Screen("shop_home_screen")
    data object HighlightSection : Screen("highlight_section")
    data object AddProduct : Screen("add_product")
    data object EditProduct : Screen("edit_product")
    data object OrderScreen : Screen("order_screen")
    data object TransactionScreen : Screen("transaction_screen")
    data object FavoriteProductsScreen : Screen("favorite_products")
    data object MapScreen : Screen("map_screen")
    data object SchedulingScreen : Screen("scheduling_screen")
    data object MyProductsScreen : Screen("my_products_screen")
    object FavoriteShopScreen : Screen("favorite_shops")
    data object NotificationScreen : Screen("notification_screen")
    data object PrivateProfile : Screen("private_profile_screen")
    data object PublicProfile : Screen("public_profile_screen")
    data object ShopPrivateScreen : Screen("shop_private_screen")
    data object CourierPrivateScreen : Screen("courier_private_screen")
    data object CustomerPrivateScreen : Screen("customer_private_screen")
    data object AdvertisingProductScreen : Screen("advertising_product_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
