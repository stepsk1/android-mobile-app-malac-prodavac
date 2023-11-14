package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.ui.theme.MP_Black

@Composable
fun TotalPrice(buyedProducts: MutableList<ProductAmount>): Double {

    var totalPrice: Double = 0.00
    for (buyedProduct in buyedProducts) {
        totalPrice += buyedProduct.totalPrice
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .padding(
                start = 5.dp,
                top = 590.dp,
                end = 5.dp,
                bottom = 40.dp
            )
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            {
                Text(
                    text = "Ukupno: ${totalPrice.toString()} rsd",
                    style = MaterialTheme.typography.h5,
                    color = MP_Black
                )
            }
        }
    }
    return totalPrice
}
