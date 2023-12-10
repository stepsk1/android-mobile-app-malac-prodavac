package com.triforce.malacprodavac.presentation.cart.DetailsOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.CartDetails.CartDetailsEvent
import com.triforce.malacprodavac.presentation.cart.CartDetails.CartDetailsViewModel
import com.triforce.malacprodavac.presentation.cart.components.BoughtProducts
import com.triforce.malacprodavac.presentation.cart.components.Confirmation
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

@Composable
fun DetailsOrderScreen(navController: NavController) {

    val viewModel: CartDetailsViewModel = hiltViewModel()
    val state = viewModel.state
//    val totalPrice: Double = state.totalPrice
    val totalPrice: Double = TotalPrice()

    val year = BoughtProducts.localDate.split("-")[0]
    val month = BoughtProducts.localDate.split("-")[1]
    val day = BoughtProducts.localDate.split("-")[2]

    var paymentMethod: String
    var deliveryMethod: String
    if (BoughtProducts.paymentMethod == PaymentMethod.OnDelivery)
        paymentMethod = "Lično/Pouzećem"
    else
        paymentMethod = "PayPal"

    if (BoughtProducts.deliveryMethod == DeliveryMethod.ByCourier)
        deliveryMethod = "Kurirska dostava"
    else
        deliveryMethod = "Lično preuzimanje"

    val scrollState = rememberScrollState()

    Box(
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize().background(MP_GreenLight).padding(vertical = 50.dp, horizontal = 20.dp)
        ) {

            Confirmation()

            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 5.dp,
                        spotColor = MP_Black,
                        shape = RoundedCornerShape(7.5.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(MP_White)
                    .padding(vertical = 20.dp, horizontal = 20.dp)
            ) {
                Column{

                    Text(
                        text = "Način plaćanja: " + paymentMethod,
                        style = MaterialTheme.typography.body1,
                        color = MP_Black
                    )

                    Text(
                        text = "Podaci za slanje: " + BoughtProducts.address,
                        style = MaterialTheme.typography.body1,
                        color = MP_Black
                    )

                    Text(
                        text = "Način slanja: " + deliveryMethod,
                        style = MaterialTheme.typography.body1,
                        color = MP_Black
                    )

                    Text(
                        text = "Ukupan iznos: $totalPrice",
                        style = MaterialTheme.typography.body1,
                        color = MP_Black
                    )

                    if (deliveryMethod == "Lično preuzimanje") {
                        Text(
                            text = "Vreme preuzimanja paketa: $day.$month.$year ${BoughtProducts.localTime}",
                            style = MaterialTheme.typography.body1,
                            color = MP_Black
                        )
                    }
                }
            }

            Button(
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                    viewModel.onEvent(CartDetailsEvent.order)
                },
                colors = ButtonDefaults.buttonColors(MP_White)
            ) {
                Text(
                    text = "Vrati se na početnu",
                    color = MP_GreenDark,
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}