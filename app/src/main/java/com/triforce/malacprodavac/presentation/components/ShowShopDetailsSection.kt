package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ShowShopDetailsSection(
    user: User?
) {
    if (user != null && user.roles.first().equals("Shop", ignoreCase = true)) {

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MP_GreenDark)
                .padding(horizontal = 20.dp)
        ){
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
            ) {
                Column {
                    Text(
                        text = "Radno vreme:",
                        style = MaterialTheme.typography.body1,
                        color = MP_White,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        text = "Pon - Pet | 08:00 - 13:00",
                        style = MaterialTheme.typography.body1,
                        color = MP_White,
                        fontWeight = FontWeight.W400
                    )
                }

                Column {
                    Text(
                        text = "Kontakt:",
                        style = MaterialTheme.typography.body1,
                        color = MP_White,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        text = "BB, Jagodina, 35000",
                        style = MaterialTheme.typography.body1,
                        color = MP_White,
                        fontWeight = FontWeight.W400
                    )
                }

                Column {
                    Text(
                        text = "Adresa:",
                        style = MaterialTheme.typography.body1,
                        color = MP_White,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        text = "+381 (66) 123-123",
                        style = MaterialTheme.typography.body1,
                        color = MP_White,
                        fontWeight = FontWeight.W400
                    )
                }
            }

            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Izmeni",
                tint = MP_GreenLight,
                modifier = Modifier
                    .size(200.dp)
            )
        }
    }
}