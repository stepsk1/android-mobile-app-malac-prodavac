package com.triforce.malacprodavac.presentation.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.orders.components.OrderProductSection
import com.triforce.malacprodavac.presentation.profile.profilePublic.ProfilePublicViewModel
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.presentation.transactions.components.TransactionSection
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White


@Composable
fun TransactionScreen(navController: NavController, viewModel: TransactionViewModel = hiltViewModel()) {

    var profileViewModel: ProfilePublicViewModel = hiltViewModel()
    val profileState = profileViewModel.state
    val state = viewModel.state

    val transactions: List<Order> = state.orders
    var color: Color

    if (profileState.user?.roles?.first().equals("Courier", ignoreCase = true) || profileState.user?.roles?.first().equals("Shop", ignoreCase = true)){
        color = MP_Pink
    }
    else
        color = MP_Green

    Box(
        modifier = Modifier
            .background(MP_GreenDark)
            .fillMaxSize()
    ){
        LinearGradient(color1 = color, color2 = color)

        Column {
            GoBackComp("Moje transakcije", navController)

            TransactionSection(
                transactions = transactions,
                viewModel = viewModel)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .padding(
                    start = 7.5.dp,
                    top = 643.dp,
                    end = 7.5.dp,
                    bottom = 40.dp
                )
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(text = "Broj ostvarenih paketa: 17", color = MP_White)

                Button(
                    onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(MP_White)
                ) {
                    Text(
                        text = "Vrati se na početnu stranu",
                        color = color,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

        BottomNavigationMenu(
            navController = navController,
            items = listOf(
                BottomNavigationMenuContent(
                    title = "Početna",
                    graphicID = Icons.Default.Home,
                    screen = Screen.HomeScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Market",
                    graphicID = Icons.Default.Star,
                    screen = Screen.StoreScreen,
                    isActive = true
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