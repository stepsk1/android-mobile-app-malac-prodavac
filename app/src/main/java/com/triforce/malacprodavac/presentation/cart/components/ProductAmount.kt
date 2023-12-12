package com.triforce.malacprodavac.presentation.cart.components

import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.shops.Shop

data class ProductAmount(
    val product: Product,
    val shop: Shop?,
    var amount: Int = 1,
    var totalPrice: Double = product.price * amount
)
