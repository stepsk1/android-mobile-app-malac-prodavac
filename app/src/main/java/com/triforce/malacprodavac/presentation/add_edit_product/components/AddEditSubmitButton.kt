package com.triforce.malacprodavac.presentation.add_edit_product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun AddEditSubmitButton(
    modifier: Modifier = Modifier,
    isEdit: Boolean
) {
    val tintColor = if (isEdit) {
        MP_Orange_Dark
    } else {
        MP_GreenDark
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 25.dp,
                start = 5.dp,
                end = 5.dp
            )
            .shadow(
                elevation = 10.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MP_White)
            .padding(
                vertical = 10.dp,
            )
    ) {
        Icon(
            imageVector = if (isEdit) {
                Icons.Outlined.Edit
            } else {
                Icons.Outlined.Check
            },
            contentDescription = "FavoriteBorder",
            tint = tintColor,
            modifier = Modifier
                .size(50.dp)
        )

        Text(
            text = if (isEdit) {
                "Izmeni proizvod"
            } else {
                "Dodaj proizvod"
            },
            style = MaterialTheme.typography.h5,
            color = MP_White,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(tintColor)
                .width(width = 250.dp)
                .padding(vertical = 10.dp)
        )
    }
}