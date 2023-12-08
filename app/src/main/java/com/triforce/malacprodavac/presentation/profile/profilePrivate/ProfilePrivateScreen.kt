package com.triforce.malacprodavac.presentation.profile.profilePrivate

//import coil.compose.AsyncImage
//import coil.request.ImageRequest
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement
import com.triforce.malacprodavac.presentation.components.ShowHighlightSectionComp
import com.triforce.malacprodavac.presentation.components.ShowShopDetailsSection
import com.triforce.malacprodavac.presentation.product.ProductOptions
import com.triforce.malacprodavac.presentation.profile.components.ProfilePrivateHeroComp
import com.triforce.malacprodavac.presentation.profile.components.ShopDescComp
import com.triforce.malacprodavac.presentation.profile.profilePrivate.userScreens.CourierPrivateScreen
import com.triforce.malacprodavac.presentation.profile.profilePrivate.userScreens.CustomerPrivateScreen
import com.triforce.malacprodavac.presentation.profile.profilePrivate.userScreens.ShopPrivateScreen
import com.triforce.malacprodavac.ui.theme.MP_White

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

