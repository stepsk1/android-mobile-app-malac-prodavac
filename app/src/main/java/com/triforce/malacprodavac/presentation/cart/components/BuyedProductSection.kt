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
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.presentation.cart.CartViewModel


@Composable
fun BuyedProductSection(
    buyedProducts: MutableList<ProductAmount>,
    viewModel: CartViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                bottom = 50.dp
            ), // 130 dp bottom padding because navigation and total price
            modifier = Modifier
                .requiredHeight(530.dp)
                .padding(top = 20.dp)
        ) {
            items(buyedProducts.size) {// how many items do we have
                // define one of items
                BuyedProductItem(
                    buyedProduct = buyedProducts[it],
                    viewModel = viewModel
                )
            }
        }
    }
}
