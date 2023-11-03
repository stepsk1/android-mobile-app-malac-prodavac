package com.triforce.malacprodavac.presentation.cart

data class BuyedProduct(
    val name: String,
    val price: Double,
    val amount: Int = 1,
    val total: Double = price * amount
)
