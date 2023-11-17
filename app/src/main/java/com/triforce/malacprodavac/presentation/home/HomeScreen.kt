package com.triforce.malacprodavac.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
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
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.state

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_GreenDark, color2 = MP_GreenLight)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
                .padding(top = 100.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(color = MP_Pink)
        ) {

        }
        Column {
            GreetingSection(msg = "Početna strana", subMsg = "Pretražite Malac Prodavac")

            Spacer(modifier = Modifier.padding(14.dp))

            GoToStoreSection(navController)

            Spacer(modifier = Modifier.padding(14.dp))

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
                        title = "Public",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.PublicProfile
                    ),
                    Feature(
                        id = 1,
                        title = "Private",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Green,
                        color2 = MP_Green,
                        screen = Screen.PrivateProfile
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
                    title = "Public",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.PublicProfile,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Private",
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