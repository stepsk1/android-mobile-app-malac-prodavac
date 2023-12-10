package com.triforce.malacprodavac.presentation.product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.presentation.components.RatingStars
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink


@Composable
fun ProductDetails(
    product: Product,
    verticalSpace: Dp = 6.dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            )
    ) {

        Text(
            text = product.title,
            style = MaterialTheme.typography.h4,
            color = MP_Black,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.padding(verticalSpace))

        Text(
            text = product.price.toString() + " rsd",
            style = MaterialTheme.typography.h6,
            color = MP_Green,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.padding(verticalSpace))

        Text(
            text = product.desc,
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            softWrap = true
        )
        Spacer(modifier = Modifier.padding(verticalSpace))

        Text(
            text = "Ostavi ocenu:",
            style = MaterialTheme.typography.body1,
            color = MP_Pink,
            fontWeight = FontWeight.W500
        )
        RatingStars(rating = product.rating!!)
    }
}