package com.triforce.malacprodavac.presentation.profile.profilePublic


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopViewModel
import com.triforce.malacprodavac.presentation.components.CallToActionFavourite
import com.triforce.malacprodavac.presentation.components.ShowHighlightSectionComp
import com.triforce.malacprodavac.presentation.components.ShowShopDetailsSection
import com.triforce.malacprodavac.presentation.profile.components.ProfileHeroComp
import com.triforce.malacprodavac.presentation.profile.components.ShopDescComp
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProfilePublicScreen(
    navController: NavController,
    viewModel: ProfilePublicViewModel = hiltViewModel(),
) {
    val viewModelFavShop: FavoriteShopViewModel = hiltViewModel()
    val state = viewModel.state

    val scrollState = rememberScrollState()
    var heightWindow = 900.dp

    if (!state.isLoading) {

        val shop = state.shop
        val user = state.shop?.user

        if (shop?.products?.isEmpty() == false) {
            heightWindow = if (shop.products.size > 3) 1400.dp
            else 1200.dp
        }

        Box(
            modifier = Modifier
                .background(MP_White)
                .verticalScroll(state = scrollState)
        ) {

            Column(
                modifier = Modifier
                    .height(heightWindow)
            ) {
                ProfileHeroComp(user, navController, false)
                Spacer(modifier = Modifier.padding(16.dp))

                Log.d("PROFILE_PUBLIC_USER", user.toString())
                Log.d("PROFILE_PUBLIC_SHOP", shop.toString())
                Log.d("PROFILE_PUBLIC_SHOP_PRODUCTS", shop?.products.toString())

                ShopDescComp(user, shop)
                Spacer(modifier = Modifier.padding(16.dp))

                ShowHighlightSectionComp(
                    navController = navController,
                    products = shop?.products,
                    title = "Naši najnoviji Proizvodi",
                    route = Screen.HighlightSection.route + "?id=${shop?.id}"
                )
                Spacer(modifier = Modifier.padding(16.dp))

                CallToActionFavourite(
                    "Ukoliko želite da pratite naš rad, kako bi znali kada smo u Vašoj okolini:",
                    viewModel,
                    viewModelFavShop
                )
                Spacer(modifier = Modifier.padding(16.dp))

                ShowShopDetailsSection(user)
            }
        }

    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}