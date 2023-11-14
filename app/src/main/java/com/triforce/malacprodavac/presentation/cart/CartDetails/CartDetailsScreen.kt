package com.triforce.malacprodavac.presentation.cart.CartDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun CartDetailsScreen(navController: NavController) {

    val buyedProducts = BuyedProducts.listOfBuyedProducts

    val typeOfPaymentOptions = listOf("Paypal", "Lično/Pouzećem")
    var selectedTypeOfPayment by remember { mutableStateOf(typeOfPaymentOptions[0]) }

    val addressesOptions = listOf(
//        "Živorada Kostića, Jagodina 35000, 066/251-101"
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
        Surface(
            color = MP_White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
                .padding(top = 67.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
        ) {

        }

        Column {

            HeaderSectionTitleWithoutIcon("Detalji plaćanja", navController)

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

        TotalPrice(buyedProducts = buyedProducts)


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

@Composable
fun HeaderSectionTitleWithoutIcon(
    msg: String,
    navController: NavController,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(width = 240.dp, height = 35.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Search",
                tint = MP_White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.popBackStack()
                        //navController.navigate(Screen.HomeScreen.route)
                    }
            )

            androidx.compose.material3.Text(
                text = msg,
                style = MaterialTheme.typography.h5,
                color = MP_White,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
    }
}
