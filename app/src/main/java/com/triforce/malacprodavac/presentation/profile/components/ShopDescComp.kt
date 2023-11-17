package com.triforce.malacprodavac.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ShopDescComp(
    user: User?
) {
    if (user != null) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                //.background(MP_Pink)
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
                .height(100.dp)
        ) {
            Text(
                text = "+381 (66) 123-123 ${user.phoneNumber.toString()}",
                style = MaterialTheme.typography.h5,
                color = MP_Black,
                fontWeight = FontWeight.W600
            )
            Text(
                text = "Domaćinstvo Perun se generacijama bavi domaćom proizvodnjom sokova i sirupa od različitog voća koje se hladno cedi, zatim...",
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                fontWeight = FontWeight.W300,
                maxLines = 3
            )
        }
    }
}