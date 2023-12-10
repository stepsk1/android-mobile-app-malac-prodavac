package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayCartItems(
    cartItems: List<CartItem>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                bottom = 80.dp
            ),
            modifier = Modifier
                .requiredHeight(450.dp)
                .padding(top = 20.dp)
        ) {

            items(cartItems.size) { id ->
                if (cartItems[id].quantity > 0)
                    CartItemRow(
                        cartItem = cartItems[id]
                    )
            }
        }
    }
}