package com.triforce.malacprodavac.presentation.home.shopHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.home.components.CategoriesSection
import com.triforce.malacprodavac.presentation.home.components.GoToShopProducts
import com.triforce.malacprodavac.presentation.home.components.GoToStoreSection
import com.triforce.malacprodavac.presentation.home.components.GreetingSection
import com.triforce.malacprodavac.presentation.home.components.RecommendedFeaturesSection
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ShopHomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_GreenLight, color2 = MP_GreenDark)
        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
                .padding(top = 90.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(color = MP_Pink)
        ){

        }
        Column {
            GreetingSection(msg = "Početna strana", subMsg = "Pretražite Malac Prodavac")
            GoToShopProducts(navController)
            RecommendedFeaturesSection(
                navController = navController,
                features = listOf(
                    Feature(
                        id = -1,
                        title = "Moji proizvodi",
                        graphicID = Icons.Default.AddCircle,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.StoreScreen
                    ),
                    Feature(
                        id = -1,
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Orange,
                        color2 = MP_Orange,
                        screen = Screen.ProfileCustomer
                    ),
                    Feature(
                        id = -1,
                        title = "Omiljeno",
                        graphicID = Icons.Default.Favorite,
                        color1 = MP_Orange,
                        color2 = MP_Orange,
                        screen = Screen.HomeScreen
                    ),
                    Feature(
                        id = -1,
                        title = "Moji proizvodi",
                        graphicID = Icons.Default.ShoppingCart,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.CartScreen
                    ),
                    Feature(
                        id = -1,
                        title = "Moji proizvodi",
                        graphicID = Icons.Default.AddCircle,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.StoreScreen
                    ),
                    Feature(
                        id = -1,
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Orange,
                        color2 = MP_Orange,
                        screen = Screen.StoreScreen
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
                    title = "Proizvodi",
                    graphicID = Icons.Default.AddCircle,
                    screen = Screen.StoreScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Moj Profil",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.ProfileShopScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Korpa",
                    graphicID = Icons.Default.AddCircle,
                    screen = Screen.CartScreen,
                    isActive = false
                )
            ), modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}
