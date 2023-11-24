package com.triforce.malacprodavac.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.home.components.GoToStoreSection
import com.triforce.malacprodavac.presentation.home.components.GreetingSection
import com.triforce.malacprodavac.presentation.home.components.RecommendedFeaturesSection
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
//    val state = viewModel.state
    var viewModelFavProducts: FavoriteViewModel = hiltViewModel()
    val stateFav by remember { mutableStateOf(viewModelFavProducts.state) }

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_Green, color2 = MP_Green)
        RoundedBackgroundComp(top = 100.dp, color = MP_White)
        Column {
            GreetingSection(msg = "Malac Prodavac", subMsg = "Od sirupa do sira")
            //GoToStoreSection(navController)
            Spacer(modifier = Modifier.padding(16.dp))
            RecommendedFeaturesSection(
                navController = navController,
                features = listOf(
                    Feature(
                        id = 1,
                        title = "Market",
                        graphicID = Icons.Default.Star,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.StoreScreen
                    ),
                    Feature(
                        id = 1,
                        title = "Korpa",
                        graphicID = Icons.Default.ShoppingCart,
                        color1 = MP_Pink,
                        color2 = MP_Pink,
                        screen = Screen.CartScreen
                    ),
                    Feature(
                        id = 1,
                        title = "Javan profil",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.PublicProfile
                    ),
                    Feature(
                        id = 1,
                        title = "Privatan profil",
                        graphicID = Icons.Default.AccountBox,
                        color1 = MP_Orange_Dark,
                        color2 = MP_Orange_Dark,
                        screen = Screen.PrivateProfile
                    ),
                    Feature(
                        id = 1,
                        title = "Dodaj novi proizvod",
                        graphicID = Icons.Default.Check,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.AddEditProduct
                    ),
                    Feature(
                        id = 1,
                        title = "Moje porudžbine",
                        graphicID = ImageVector.vectorResource(R.drawable.round_featured_play_list_24),
                        color1 = MP_Pink,
                        color2 = MP_Pink,
                        screen = Screen.OrderScreen
                    ),
                    Feature(
                        id = 1,
                        title = "Moji omiljeni proizvodi",
                        graphicID = Icons.Default.Favorite,
                        color1 = MP_Pink,
                        color2 = MP_Pink,
                        screen = Screen.FavoriteProductsScreen
                    )
                )
            )
        }
        BottomNavigationMenu(
            navController = navController,
            items = listOf(
                BottomNavigationMenuContent(
                    title = "Početna",
                    graphicID = Icons.Default.Home,
                    screen = Screen.HomeScreen,
                    isActive = true
                ),
                BottomNavigationMenuContent(
                    title = "Market",
                    graphicID = Icons.Default.Star,
                    screen = Screen.StoreScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Profil",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.PublicProfile,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Privatni",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.PrivateProfile,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Korpa",
                    graphicID = Icons.Default.ShoppingCart,
                    screen = Screen.CartScreen,
                    isActive = false
                )
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}