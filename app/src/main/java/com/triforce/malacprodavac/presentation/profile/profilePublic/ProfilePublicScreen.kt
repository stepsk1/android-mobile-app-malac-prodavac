package com.triforce.malacprodavac.presentation.profile.profilePublic


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
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

    if ( state.products?.isEmpty() == false ) {
        if (state.products.size > 2) heightWindow = 1400.dp
        else heightWindow = 1200.dp
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
            ProfileHeroComp(state.currentUser, navController, false)

            Spacer(modifier = Modifier.padding(16.dp))

            ShopDescComp(state.currentUser, state.currentShop)

            Spacer(modifier = Modifier.padding(16.dp))

            if (state.products?.isEmpty() == false) {
                ShowHighlightSectionComp(
                    navController = navController,
                    products = state.products,
                    title = "Naši najnoviji Proizvodi",
                    route = Screen.HighlightSection.route + "?id=${state.currentShop!!.id}"
                )
                Spacer(modifier = Modifier.padding(16.dp))
            }

            CallToActionFavourite("Ukoliko želite da pratite naš rad, kako bi znali kada smo u Vašoj okolini:", viewModel, viewModelFavShop)

            Spacer(modifier = Modifier.padding(16.dp))

            ShowShopDetailsSection(state.currentUser)
        }
    }
}