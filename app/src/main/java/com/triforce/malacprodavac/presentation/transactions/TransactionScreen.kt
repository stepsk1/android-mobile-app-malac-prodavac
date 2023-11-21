package com.triforce.malacprodavac.presentation.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.presentation.orders.components.OrderProductSection
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.presentation.transactions.components.TransactionSection
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_White


@Composable
fun TransactionScreen(navController: NavController, viewModel: TransactionViewModel = hiltViewModel()) {

    val state = viewModel.state

    val transactions: List<Order> = state.orders

    Box(
        modifier = Modifier
            .background(MP_GreenDark)
            .fillMaxSize()
    ){
        LinearGradient(color1 = MP_Green, color2 = MP_Green)

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

                Text(text = "Broj ostvarenih paketa: 17", color = MP_White)

                Button(
                    onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(MP_White)
                ) {
                    Text(
                        text = "Vrati se na početnu stranu",
                        color = MP_Green,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}