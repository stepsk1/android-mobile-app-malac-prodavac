package com.triforce.malacprodavac.presentation.cart.components

import com.triforce.malacprodavac.domain.model.Product

data class ProductAmount(
    val product: Product,
    var amount: Int = 1,
    var totalPrice: Double = product.price * amount
)
