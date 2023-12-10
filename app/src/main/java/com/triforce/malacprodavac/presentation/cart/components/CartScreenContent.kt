package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_White
import androidx.compose.runtime.getValue

@Composable
fun CartScreenContent(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItems.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {

        DisplayCartItems(cartItems = cartItems)
        Spacer(modifier = Modifier.padding(6.dp))

        TotalPrice(viewModel)
        Spacer(modifier = Modifier.padding(6.dp))

        Button(
            onClick = {
                navController.navigate(Screen.CartDetailsScreen.route)
            },
            colors = ButtonDefaults.buttonColors(MP_Green)
        ) {
            Text(
                text = "Nastavi na plaćanje",
                color = MP_White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(horizontal = 50.dp, vertical = 6.dp)
            )
        }
    }
}