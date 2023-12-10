package com.triforce.malacprodavac.presentation.cart

sealed class CartEvent {
    object DeleteFromCart: CartEvent()
}