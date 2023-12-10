package com.triforce.malacprodavac.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.home.HomeViewModel
import com.triforce.malacprodavac.presentation.home.components.GreetingSection
import com.triforce.malacprodavac.presentation.home.components.RecommendedFeaturesSection
import com.triforce.malacprodavac.presentation.orders.OrderViewModel
import com.triforce.malacprodavac.presentation.profile.profilePublic.ProfilePublicViewModel
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.presentation.transactions.TransactionViewModel
import com.triforce.malacprodavac.presentation.transactions.components.TransactionSection
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    val user = state.currentUser
    var role = "Kupac"

    if (user != null) {
        if (user.roles.contains("Shop") == false && user.roles.contains("Courier")) {
            role = "Kurir"
        } else if (user.roles.contains("Shop")) {
            role = "Prodavac"
        }
    }
    if (!state.isLoading) {
        Box(
            modifier = Modifier
                .background(MP_White)
                .fillMaxSize()
        ) {
            LinearGradient(color1 = MP_Green, color2 = MP_Green)
            RoundedBackgroundComp(top = 100.dp, color = MP_White)

            Column {
                GreetingSection(
                    msg = "Malac ${role}",
                    subMsg = "Od sirupa do sira",
                    navController = navController
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
                        graphicID = ImageVector.vectorResource(R.drawable.logo_green),
                        screen = Screen.StoreScreen,
                        isActive = false
                    ),
                    BottomNavigationMenuContent(
                        title = "Profil",
                        graphicID = Icons.Default.Person,
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
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}