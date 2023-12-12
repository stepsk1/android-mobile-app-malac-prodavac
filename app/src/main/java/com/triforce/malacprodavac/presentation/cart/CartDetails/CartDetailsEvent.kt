package com.triforce.malacprodavac.presentation.cart.CartDetails

sealed class CartDetailsEvent {
    object order: CartDetailsEvent()
}
