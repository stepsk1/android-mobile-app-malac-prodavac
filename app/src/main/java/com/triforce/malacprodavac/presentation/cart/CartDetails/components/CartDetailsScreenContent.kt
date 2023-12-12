package com.triforce.malacprodavac.presentation.cart.CartDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.CartEvent
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.presentation.cart.components.BoughtProducts
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

@Composable
fun CartDetailsScreenContent(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItems.collectAsState()
    val totalPrice = viewModel.cartState.totalPrice

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Plaćanje",
            style = MaterialTheme.typography.h6,
            color = MP_Black
        )
        Spacer(modifier = Modifier.padding(6.dp))

        AddressType(viewModel)
        Spacer(modifier = Modifier.padding(6.dp))

        PaymentType(viewModel)
        Spacer(modifier = Modifier.padding(6.dp))

        ShippingType(viewModel)
        Spacer(modifier = Modifier.padding(6.dp))

        Text(
            text = "Ukupno: ${totalPrice} rsd, ${cartItems.size}x proizvoda.",
            style = MaterialTheme.typography.body1,
            color = MP_Black
        )
        Spacer(modifier = Modifier.padding(6.dp))

        Button(
            onClick = {
                if (viewModel.cartState.selectedShipping == DeliveryMethod.ByCourier)
                {
                    viewModel.onEvent(CartEvent.makeOrder)
                    navController.navigate(Screen.DetailsOrderScreen.route)
                }
                else
                    navController.navigate(Screen.SchedulingScreen.route)
            },
            colors = ButtonDefaults.buttonColors(MP_Green)
        ) {
            Text(
                text = "Izvrši porudžbinu",
                color = MP_White,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 50.dp, vertical = 6.dp)
            )
        }
    }
}