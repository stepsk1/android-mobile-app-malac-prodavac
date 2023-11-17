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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.presentation.components.CallToActionFavourite
import com.triforce.malacprodavac.presentation.components.ShowHighlightSectionComp
import com.triforce.malacprodavac.presentation.components.ShowShopDetailsSection
import com.triforce.malacprodavac.presentation.profile.components.ProfileHeroComp
import com.triforce.malacprodavac.presentation.profile.components.ShopDescComp
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProfilePublicScreen(navController: NavController, viewModel: ProfilePublicViewModel = hiltViewModel()) {
    val state = viewModel.state
    if (!viewModel.isLoggedIn())
        LaunchedEffect(Unit) {
            navController.navigate(Screen.LoginScreen.route)
        }

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .background(MP_White)
    ){

        Column(
            modifier = Modifier
                .height(1400.dp)
        ){
            ProfileHeroComp(state.currentUser, navController, false)

            if (state.currentUser?.roles?.first().equals("Shop", ignoreCase = true)){

                Spacer(modifier = Modifier.padding(15.dp))

                ShopDescComp(state.currentUser)

                Spacer(modifier = Modifier.padding(15.dp))

                ShowHighlightSectionComp(
                    navController = navController,
                    products = listOf( Product(1,2,3,true,99.0,"RSD",9.0,null,null,null,null,"RSD","Prsuta 100g", "", null, null,"","",null,null), Product(1,2,3,true,99.0,"RSD",9.0,null,null,null,null,"RSD","Prsuta 100g", "", null, null,"","",null,null)),
                    title = "Najpopularniji Proizvodi",
                    route = Screen.HighlightSection.route
                )

                Spacer(modifier = Modifier.padding(5.dp))

                CallToActionFavourite("Ukoliko želite da pratite naš blog, kako bi znali kada smo u Vašoj okolini:")

                Spacer(modifier = Modifier.padding(10.dp))

                ShowHighlightSectionComp(
                    navController = navController,
                    products = listOf( Product(1,2,3,true,99.0,"RSD",9.0,null,null,null,null,"RSD","Prsuta 100g", "", null, null,"","",null,null), Product(1,2,3,true,99.0,"RSD",9.0,null,null,null,null,"RSD","Prsuta 100g", "", null, null,"","",null,null)),
                    title = "Najnoviji Proizvodi",
                    route = Screen.HighlightSection.route
                )

                Spacer(modifier = Modifier.padding(10.dp))

                //ShowCommentsSection()
                ShowShopDetailsSection(state.currentUser)
            }
        }
    }

}