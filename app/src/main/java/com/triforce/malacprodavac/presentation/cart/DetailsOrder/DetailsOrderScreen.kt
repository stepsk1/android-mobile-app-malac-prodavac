package com.triforce.malacprodavac.presentation.cart.DetailsOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.CartDetails.CartDetailsEvent
import com.triforce.malacprodavac.presentation.cart.CartDetails.CartDetailsViewModel
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.cart.components.Confirmation
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

@Composable
fun DetailsOrderScreen(navController: NavController) {

    val viewModel: CartDetailsViewModel = hiltViewModel()
    val state = viewModel.state
//    val totalPrice: Double = state.totalPrice
    val totalPrice: Double = TotalPrice()

    var paymentMethod: String
    var deliveryMethod: String
    if (BuyedProducts.paymentMethod == PaymentMethod.OnDelivery)
        paymentMethod = "Lično/Pouzećem"
    else
        paymentMethod = "PayPal"

    if(BuyedProducts.deliveryMethod == DeliveryMethod.ByCourier)
        deliveryMethod = "Kurirska dostava"
    else
        deliveryMethod = "Lično preuzimanje"

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
            .verticalScroll(state = scrollState)
    ) {
        LinearGradient(color1 = MP_GreenLight, color2 = MP_GreenDark )
        RoundedBackgroundComp(top = 65.dp, color = MP_White)

        Column {
            GoBackNoSearch("Detalji porudžbine", navController)

            Spacer(modifier = Modifier.height(7.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){
                Confirmation()

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = 5.dp,
                            spotColor = MP_Black,
                            shape = RoundedCornerShape(7.5.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(MP_White)
                        .padding(vertical = 20.dp, horizontal = 20.dp)
                ){
                    Column(
                        modifier = Modifier.padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {

                        Text(
                            text = "Način plaćanja: " + paymentMethod,
                            style = MaterialTheme.typography.body1,
                            color = MP_Black,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(Alignment.Start)
                        )

                        Text(
                            text = "Podaci za slanje: " + BuyedProducts.address,
                            style = MaterialTheme.typography.body1,
                            color = MP_Black,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(Alignment.Start)
                        )

                        Text(
                            text = "Način slanja: " + deliveryMethod,
                            style = MaterialTheme.typography.body1,
                            color = MP_Black,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(Alignment.Start)
                        )

                        Text(
                            text = "Ukupan iznos: $totalPrice",
                            style = MaterialTheme.typography.body1,
                            color = MP_Black,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(Alignment.Start)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .padding(
                            start = 5.dp,
                            top = 1.dp, //663.dp,
                            end = 5.dp,
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
                                navController.navigate(Screen.HomeScreen.route)
                                viewModel.onEvent(CartDetailsEvent.order)
                            },
                            colors = ButtonDefaults.buttonColors(MP_Orange_Dark)
                        ) {
                            Text(
                                text = "Vrati se na naslovnu",
                                color = MP_White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}