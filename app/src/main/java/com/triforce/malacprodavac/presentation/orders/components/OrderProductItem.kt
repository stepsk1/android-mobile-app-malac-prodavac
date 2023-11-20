package com.triforce.malacprodavac.presentation.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.presentation.orders.OrderViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Pink
import androidx.compose.runtime.Composable
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun OrderProductItem(
    order: Order,
    viewModel: OrderViewModel
){
//    var state = viewModel.state

    var orderStatus = order.orderStatus
    var orderDeliveryMethod = order.deliveryMethod
    if (orderStatus == "Ordered")
        orderStatus = "Na čekanju..."
    else if(orderStatus == "Packaged")
        orderStatus = "Upakovano"
    else if(orderStatus == "InDelivery")
        orderStatus = "U isporuci"
    else if(orderStatus == "Received")
        orderStatus = "Primljeno"
    else
        orderStatus = "Vraćeno"

    if ( orderDeliveryMethod == "ByCourier")
        orderDeliveryMethod = "Kurirska dostava"
    else
        orderDeliveryMethod = "Lično preuzimanje"

        BoxWithConstraints(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .shadow(
                    elevation = 5.dp,
                    spotColor = MP_Black,
                    shape = RoundedCornerShape(3.5.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .background(MP_White)
                .fillMaxWidth()
                .requiredHeight(130.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                    Text(
                            text = orderStatus,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.body1,
                            color = MP_Pink,
                            modifier = Modifier
                            .align(Alignment.TopStart)
                        )

                    Text(
                        text = "Pršuta 100g " + order.quantity.toString() + "X" ,
                        style = MaterialTheme.typography.body1,
                        color = MP_Black,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    )

                    Text(
                        text = orderDeliveryMethod,
                        style = MaterialTheme.typography.body1,
                        color = MP_Black,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .requiredWidth(130.dp)
                            .requiredHeight(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Add",
                            tint = MP_Pink,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(30.dp)
                                .clickable {

                                }
                        )
                    }
            }
        }
}