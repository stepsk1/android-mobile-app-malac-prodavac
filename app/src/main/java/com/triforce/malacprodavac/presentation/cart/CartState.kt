package com.triforce.malacprodavac.presentation.cart

import com.triforce.malacprodavac.presentation.cart.components.ProductAmount

data class CartState (
    val totalPrice: Double = 0.00,
    val isDeleted: Boolean = false,
)