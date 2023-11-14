package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink

@Composable
fun BuyedProductItem(
    buyedProduct: Product
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(3.5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
            .background(MP_Gray)
            .requiredHeight(130.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {

            Text(
                text = buyedProduct.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                color = MP_Black,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )

            Text(
                text = buyedProduct.price.toString() + " rsd",
                style = MaterialTheme.typography.body1,
                color = MP_Green,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "1X",
                style = MaterialTheme.typography.h6,
                color = MP_Black,
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )

            Box(
                modifier = Modifier
                    .padding(7.5.dp)
                    .align(Alignment.BottomEnd)
                    .requiredWidth(100.dp)
                    .requiredHeight(90.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add",
                    tint = MP_Green,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.BottomStart)
                        .clickable {
                            //buyedProduct.amount = buyedProduct.amount + 1
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.round_remove_circle_24),
                    contentDescription = "Remove one",
                    tint = MP_Pink,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.BottomCenter)
                        .clickable {
//                            if (buyedProduct.amount > 1)
//                                buyedProduct.amount = buyedProduct.amount - 1
                        }
                )

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete one",
                    tint = MP_Orange_Dark,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.BottomEnd)
                        .clickable {

                        }
                )
            }
        }
    }
}
