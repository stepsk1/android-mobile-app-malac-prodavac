package com.triforce.malacprodavac.presentation.cart

sealed class CartEvent {
    object quantityChange : CartEvent()
    object makeOrder : CartEvent()
}