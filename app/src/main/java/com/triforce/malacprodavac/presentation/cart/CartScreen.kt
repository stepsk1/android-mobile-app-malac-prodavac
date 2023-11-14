package com.triforce.malacprodavac.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.components.BuyedProductSection
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun CartScreen(navController: NavController)
{

    var buyedProducts = BuyedProducts.listOfBuyedProducts
    val buyedProductsSet = buyedProducts.toMutableSet()
    buyedProducts = buyedProductsSet.toMutableList()
    val viewModel: CartViewModel = hiltViewModel()
    val state = viewModel.state

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ){
        LinearGradient(color1 = MP_Orange, color2 = MP_Orange_Dark )

        RoundedBackgroundComp(top = 65.dp, color = MP_White)

        Column {
            GoBackComp("Moja korpa", navController)
            BuyedProductSection(
                buyedProducts = buyedProducts,
                viewModel = viewModel
            )
        }

        TotalPrice(buyedProducts = buyedProducts)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .padding(
                    start = 7.5.dp,
                    top = 663.dp,
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
                Button(
                    onClick = {
                        navController.navigate(Screen.CartDetailsScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(MP_Green)
                ) {
                    Text(
                        text = "Nastavi sa plaćanjem",
                        color = MP_White,
                        style = androidx.compose.material.MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}
