package com.triforce.malacprodavac.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import com.triforce.malacprodavac.ui.theme.MP_White
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
import com.triforce.malacprodavac.presentation.home.components.GoToStoreSection
import com.triforce.malacprodavac.presentation.home.components.GreetingSection
import com.triforce.malacprodavac.presentation.home.components.RecommendedFeaturesSection
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Pink

@Composable
fun HomeScreen(navController: NavController) {
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
            CategoriesSection(categories = listOf("Pijaca", "Profil", "Porudžbine", "Korpa", "Prodavnica"))
            GoToStoreSection(navController)
            RecommendedFeaturesSection(
                navController = navController,
                features = listOf(
                    Feature(
                        id = 1,
                        title = "Prodavnica",
                        graphicID = Icons.Default.Menu,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.StoreScreen
                    ),
                    Feature(
                        id = 1,
                        title = "Korpa",
                        graphicID = Icons.Default.ShoppingCart,
                        color1 = MP_Orange,
                        color2 = MP_Orange,
                        screen = Screen.CartScreen
                    ),
                    Feature(
                        id = 1,
                        title = "Prodavnica",
                        graphicID = Icons.Default.Search,
                        color1 = MP_Orange,
                        color2 = MP_Orange,
                        screen = Screen.StoreScreen
                    ),
                    Feature(
                        id = 1,
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.ProfileCustomer
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
                    title = "Prodavnica",
                    graphicID = Icons.Default.AddCircle,
                    screen = Screen.StoreScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Moj Profil",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.ProfileCustomer,
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