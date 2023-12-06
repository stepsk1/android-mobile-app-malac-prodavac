package com.triforce.malacprodavac.presentation.cart.CartDetails

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButtonColors
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
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
        LinearGradient(color1 = MP_Green, color2 = MP_GreenDark )
        RoundedBackgroundComp(top = 65.dp, color = MP_White)
        GoBackNoSearch("Detalji plaćanja", navController)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight().padding(horizontal = 20.dp, vertical = 90.dp)
        ) {

            Text(
                text = "Unesite detalje plaćanja",
                style = MaterialTheme.typography.h5,
                color = MP_Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Način plaćanja",
                style = MaterialTheme.typography.body1,
                color = MP_Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 5.dp,
                        spotColor = MP_Black,
                        shape = RoundedCornerShape(7.5.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(MP_Gray)
                    .padding(vertical = 6.dp)
            ) {
                Column {
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
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Podaci za slanje",
                style = MaterialTheme.typography.body1,
                color = MP_Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 5.dp,
                        spotColor = MP_Black,
                        shape = RoundedCornerShape(7.5.dp)
                    )
                    .clip(RoundedCornerShape(6.dp))
                    .background(MP_Gray)
                    .padding(vertical = 6.dp)
            ) {
                Column {
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
                                style = MaterialTheme.typography.body1,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Način slanja",
                style = MaterialTheme.typography.body1,
                color = MP_Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 5.dp,
                        spotColor = MP_Black,
                        shape = RoundedCornerShape(7.5.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(MP_Gray)
                    .padding(vertical = 6.dp)
            ) {
                Column {
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
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            TotalPrice(viewModel = viewModelCart)
            Spacer(modifier = Modifier.height(6.dp))

            Column(
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
                        if (orderProducts.deliveryMethod == DeliveryMethod.ByCourier)
                            navController.navigate(Screen.DetailsOrderScreen.route)
                        else
                            navController.navigate(Screen.SchedulingScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(MP_Green)
                ) {
                    Text(
                        text = "Izvrši Porudžbinu",
                        color = MP_White,
                        style = androidx.compose.material.MaterialTheme.typography.body1
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