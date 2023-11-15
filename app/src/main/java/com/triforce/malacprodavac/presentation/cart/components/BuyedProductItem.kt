package com.triforce.malacprodavac.presentation.cart.components

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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.CartEvent
import com.triforce.malacprodavac.presentation.cart.CartViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink

@Composable
fun BuyedProductItem(
    buyedProduct: ProductAmount,
    viewModel: CartViewModel
) {
    var amount by remember { mutableStateOf(buyedProduct.amount) }
    var productTotalPrice by remember { mutableStateOf(buyedProduct.totalPrice) }

    fun removeFromBuyedProducts(item: ProductAmount) {
        BuyedProducts.listOfBuyedProducts.remove(item)
    }
    val buyedProduct by remember { mutableStateOf(buyedProduct) }

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

            if (amount > 0) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = buyedProduct.product.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        color = MP_Black
                    )
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Delete one",
                        tint = MP_Pink,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                viewModel.onEvent(CartEvent.DeleteFromCart)
                                removeFromBuyedProducts(buyedProduct)
                                buyedProduct.amount = 0
                                amount = 0
                            }
                    )
                }

                Text(
                    text = productTotalPrice.toString() + " rsd",
                    style = MaterialTheme.typography.body1,
                    color = MP_Green,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = amount.toString() + "X",
                    style = MaterialTheme.typography.h6,
                    color = MP_Black,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .requiredWidth(130.dp)
                        .requiredHeight(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add",
                        tint = MP_Green,
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.BottomCenter)
                            .clickable {
                                amount++
                                buyedProduct.amount++
                                buyedProduct.totalPrice = buyedProduct.amount * buyedProduct.product.price
                                productTotalPrice = amount * buyedProduct.product.price
                            }
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.round_remove_circle_24),
                        contentDescription = "Remove one",
                        tint = MP_Orange_Dark,
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.BottomEnd)
                            .clickable {
                                if (amount > 1) {
                                    amount--
                                    buyedProduct.amount--
                                    buyedProduct.totalPrice = buyedProduct.amount * buyedProduct.product.price
                                    productTotalPrice = amount * buyedProduct.product.price
                                }
                            }
                    )
                }
            }
        }
    }
}