package com.triforce.malacprodavac.presentation.cart.CartDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray

@Composable
fun PaymentType (
    viewModel: CartViewModel
) {
    Text(
        text = "Način plaćanja:",
        style = MaterialTheme.typography.body1,
        color = MP_Black
    )

    Column(
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
        viewModel.cartState.paymentOptions.forEach { (paymentOption, displayName) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (paymentOption == viewModel.cartState.selectedPayment),
                    onClick = {
                        viewModel.cartState = viewModel.cartState.copy(selectedPayment = paymentOption)
                    }
                )
                Text(
                    text = displayName,
                    style = MaterialTheme.typography.body1,
                    color = MP_Black
                )
            }
        }
    }
}