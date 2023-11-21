package com.triforce.malacprodavac.presentation.cart.CartDetails

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

@Composable
fun CartDetailsScreen(navController: NavController, viewModel: CartDetailsViewModel = hiltViewModel()) {

    var viewModelCart: CartViewModel = hiltViewModel()
    val orderProducts = BuyedProducts

    val typeOfPaymentOptions = listOf("Paypal", "Lično/Pouzećem")
    var selectedTypeOfPayment by remember { mutableStateOf(typeOfPaymentOptions[0]) }

    val addressesOptions = listOf(
        "Gavrila Principa, Kragujevac, 066/251-102"
    )

    var selectedAddress by remember { mutableStateOf(addressesOptions[0]) }

    val typeOfSendingOptions = listOf("Lično preuzimanje", "Kurirska dostava")
    var selectedTypeOfSending by remember { mutableStateOf(typeOfSendingOptions[0]) }

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_Orange, color2 = MP_Orange_Dark)

        RoundedBackgroundComp(top = 65.dp, color = MP_White)

        Column {

            GoBackNoSearch("Detalji plaćanja", navController)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Način plaćanja",
                style = MaterialTheme.typography.h5,
                color = MP_Black,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(start = 7.dp, end = 7.dp, bottom = 3.dp, top = 3.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = 5.dp,
                            spotColor = MP_Black,
                            shape = RoundedCornerShape(7.5.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(MP_Gray)
                        .padding(vertical = 7.dp, horizontal = 7.dp)
                ){
                    Column(
                        modifier = Modifier.padding(2.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        typeOfPaymentOptions.forEach { payment ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = (payment == selectedTypeOfPayment),
                                    onClick = { selectedTypeOfPayment = payment }
                                )
                                Text(
                                    text = payment,
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Podaci za slanje",
                    style = MaterialTheme.typography.h5,
                    color = MP_Black,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(
                    text = "Izmeni",
                    style = MaterialTheme.typography.h6,
                    color = MP_White,
                    modifier = Modifier
                        .padding(start = 80.dp, top = 5.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MP_Pink)
                        .padding(start = 7.5.dp, end = 7.5.dp, top = 2.dp, bottom = 2.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(start = 7.dp, end = 7.dp, bottom = 3.dp, top = 3.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = 5.dp,
                            spotColor = MP_Black,
                            shape = RoundedCornerShape(7.5.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(MP_Gray)
                        .padding(vertical = 7.dp, horizontal = 7.dp)
                ){
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.spacedBy(13.dp),
                    ) {
                        addressesOptions.forEach { address ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = (address == selectedAddress),
                                    onClick = { selectedAddress = address }
                                )
                                Text(
                                    text = address,
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Način slanja",
                style = MaterialTheme.typography.h5,
                color = MP_Black,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ){
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(start = 7.dp, end = 7.dp, bottom = 3.dp, top = 3.dp)
                        .fillMaxWidth()
                        .shadow(
                            elevation = 5.dp,
                            spotColor = MP_Black,
                            shape = RoundedCornerShape(7.5.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(MP_Gray)
                        .padding(vertical = 7.dp, horizontal = 7.dp)
                ){
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        typeOfSendingOptions.forEach { typeOfSending ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = (typeOfSending == selectedTypeOfSending),
                                    onClick = { selectedTypeOfSending = typeOfSending }
                                )
                                Text(
                                    text = typeOfSending,
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        TotalPrice(viewModel = viewModelCart)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .padding(
                    start = 5.dp,
                    top = 663.dp,
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
                        if (selectedTypeOfPayment == "Paypal")
                            orderProducts.paymentMethod = PaymentMethod.PayPal
                        else
                            orderProducts.paymentMethod = PaymentMethod.OnDelivery
                        orderProducts.address = selectedAddress
                        if (selectedTypeOfSending == "Lično preuzimanje")
                            orderProducts.deliveryMethod = DeliveryMethod.SelfPickup
                        else
                            orderProducts.deliveryMethod = DeliveryMethod.ByCourier
                        navController.navigate(Screen.DetailsOrderScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(MP_Orange_Dark)
                ) {
                    Text(
                        text = "Izvrši porudžbinu",
                        color = MP_White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
