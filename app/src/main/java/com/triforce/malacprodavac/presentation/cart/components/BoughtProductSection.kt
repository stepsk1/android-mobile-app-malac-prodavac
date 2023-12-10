package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.presentation.cart.CartViewModel


@Composable
fun BoughtProductsSection(
    boughtProducts: MutableList<ProductAmount>,
    viewModel: CartViewModel
) {

    var totalPriceOfAllOrders: Double = 0.00

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                bottom = 80.dp
            ),
            modifier = Modifier
                .requiredHeight(450.dp)
                .padding(top = 20.dp)
        ) {

            for (order in boughtProducts) {
                totalPriceOfAllOrders += order.totalPrice
            }

            items(boughtProducts.size) {
                    BoughtProductItem(
                        boughtProduct = boughtProducts[it],
                        viewModel = viewModel,
                        totalPrice = totalPriceOfAllOrders
                    )
            }
        }
    }
}
