package com.triforce.malacprodavac.presentation.cart.components

import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.shops.Shop

data class CartItem(
    val product: Product,
    val shop: Shop?,
    var quantity: Int = 1,
    var price: Double = 0.00
)