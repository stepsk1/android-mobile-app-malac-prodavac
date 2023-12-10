package com.triforce.malacprodavac.data.repository.cart

import com.triforce.malacprodavac.presentation.cart.components.CartItem

object CartRepository {
    private val cartItems: MutableList<CartItem> = mutableListOf()

    fun getCartItems(): List<CartItem> {
        return cartItems
    }

    fun addItemToCart(item: CartItem) {
        cartItems.add(item)
    }

    fun removeItemFromCart(item: CartItem) {
        cartItems.remove(item)
    }

    fun clearCart() {
        cartItems.clear()
    }
}
