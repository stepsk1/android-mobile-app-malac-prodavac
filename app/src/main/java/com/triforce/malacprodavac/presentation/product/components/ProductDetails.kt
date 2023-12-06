package com.triforce.malacprodavac.presentation.product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.presentation.components.RatingStars
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green


@Composable
fun ProductDetails(
    product: Product,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp, end = 20.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 20.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.h5,
                    color = MP_Black,
                    fontWeight = FontWeight.W500
                )
                RatingStars(rating = product.rating!!)
            }


            Text(
                text = product.price.toString() + " rsd",
                style = MaterialTheme.typography.h5,
                color = MP_Green,
                fontWeight = FontWeight.W500
            )
        }
        Text(
            text = product.desc,
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            softWrap = true
        )
    }
}