package com.triforce.malacprodavac.presentation.cart

import com.triforce.malacprodavac.presentation.cart.components.CartItem
import com.triforce.malacprodavac.presentation.product.ProductEvent

sealed class CartEvent {
    object quantityChange : CartEvent()
    object makeOrder : CartEvent()
}