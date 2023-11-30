package com.triforce.malacprodavac.presentation.orders.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.presentation.orders.OrderState
import com.triforce.malacprodavac.presentation.orders.OrderViewModel

@Composable
fun OrderProductSection(
    orders: List<Order>,
    viewModel: OrderViewModel,
    orderedProducts: MutableList<Product>
) {
     var state by remember { mutableStateOf(OrderState()) }

    var product: Product? = null

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
            items(orders.size) {// how many items do we have
            // define one of items
                product = orderedProducts.get(orders.size - it - 1)

                if(product != null){
                    OrderProductItem(
                        order = orders[orders.size - it - 1],
                        viewModel = viewModel,
                        product = product!!
                    )
                }
            }
        }
    }
}