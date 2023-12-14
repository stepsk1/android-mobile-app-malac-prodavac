package com.triforce.malacprodavac.presentation.store.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun CategorySectionHeader(
    title: String,
    sub: String,
    colorBackground: Color
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .background(colorBackground, RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MP_White,
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(0.5F)
            )
            Text(
                text = sub,
                style = MaterialTheme.typography.body2,
                color = MP_White,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth(0.6F)
            )
        }
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Malac Prodavac",
            tint = MP_White,
            modifier = Modifier
                .size(100.dp)
        )
    }
}