package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.shops.Shop

data class CartItem(
    val product: Product,
    val shop: Shop?,
    var quantity: MutableState<Int> = mutableStateOf(1),
    var price: MutableState<Double> = mutableStateOf(product.price)
)
