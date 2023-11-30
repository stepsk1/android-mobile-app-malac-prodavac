package com.triforce.malacprodavac.presentation.cart

sealed class CartEvent {
    data class AddToCart(val totalPrice: Double): CartEvent()
    data class RemoveFromCart(val totalPrice: Double): CartEvent()
    data class getTotalPrice(val totalPrice: Double): CartEvent()
    object DeleteFromCart: CartEvent()
}