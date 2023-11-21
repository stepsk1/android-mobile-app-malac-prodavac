package com.triforce.malacprodavac.presentation.cart

data class CartState (
    val totalPrice: Double = 0.00,
    val isDeleted: Boolean = false
)