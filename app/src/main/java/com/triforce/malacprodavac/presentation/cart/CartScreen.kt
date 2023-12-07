package com.triforce.malacprodavac.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.cart.components.BuyedProductSection
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun CartScreen(navController: NavController) {

    var buyedProducts = BuyedProducts.listOfBuyedProducts
    val buyedProductsSet = buyedProducts.toMutableSet()
    buyedProducts = buyedProductsSet.toMutableList()
    val viewModel: CartViewModel = hiltViewModel()

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_Green, color2 = MP_GreenDark)
        RoundedBackgroundComp(top = 65.dp, color = MP_White)

        Column {
            GoBackNoSearch(msg = "Moja korpa", modifier = Modifier.clickable {
                navController.popBackStack()
            })

            BuyedProductSection(
                buyedProducts = buyedProducts,
                viewModel = viewModel,
            )
            Spacer(modifier = Modifier.padding(6.dp))

            TotalPrice(viewModel)
            Spacer(modifier = Modifier.padding(6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp))
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.CartDetailsScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(MP_GreenDark)
                    ) {
                        Text(
                            text = "Nastavi Dalje",
                            color = MP_White,
                            style = androidx.compose.material.MaterialTheme.typography.body1
                        )
                    }
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
                    isActive = true
                )
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
