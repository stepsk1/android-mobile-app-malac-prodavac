package com.triforce.malacprodavac.presentation.profile.profilePrivate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddLink
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun AdvertisingProductButton(
    modifier: Modifier = Modifier,
    product: Product?,
    navController: NavController,
    advertising: Boolean,
    change: Boolean
) {
    var colorTint = MP_Orange_Dark
    var start = 20.dp
    if (advertising)
        colorTint = MP_Green

    val msg = "Oglasi proizvod"
    if(change)
        start = 50.dp
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                bottom=25.dp, start = start, end = 20.dp
            )
            .shadow(
                elevation = 10.dp, spotColor = MP_Black, shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MP_White)
            .padding(
                vertical = 10.dp, horizontal = 16.dp
            )
    ) {
        Icon(
            Icons.Outlined.PostAdd,
            contentDescription = "Advertising",
            tint = colorTint,
            modifier = Modifier.size(35.dp).padding(end = 6.dp)
        )
        Text(text = msg,
            style = MaterialTheme.typography.body1,
            color = MP_White,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    if(!advertising)
                        navController.navigate(Screen.MyProductsScreen.route)
                    else
                        navController.navigate(Screen.AdvertisingProductScreen.route + "?productId=${product?.id}")
                }
                .clip(RoundedCornerShape(20.dp))
                .background(colorTint)
                .width(width = 200.dp)
                .padding(vertical = 10.dp))
    }
}