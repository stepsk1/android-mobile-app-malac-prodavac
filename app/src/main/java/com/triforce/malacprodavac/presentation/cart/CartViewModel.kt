package com.triforce.malacprodavac.presentation.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
): ViewModel() {

    var state by mutableStateOf(CartState())

    fun onEvent(event: CartEvent) {
        when(event) {
            is CartEvent.AddToCart -> {
                state = state.copy(totalPrice = event.totalPrice)
            }
            is CartEvent.RemoveFromCart -> {
                state = state.copy(totalPrice = event.totalPrice)
            }
            is CartEvent.DeleteFromCart -> {
                state = state.copy(isDeleted = true)
            }
            is CartEvent.getTotalPrice -> {
                countTotalPrice()
            }
            else -> { }
        }
    }

    private fun countTotalPrice() {
        var totalPrice: Double = 0.00
        for (buyedProduct in BuyedProducts.listOfBuyedProducts) {
            totalPrice += buyedProduct.totalPrice
        }
        state = state.copy(totalPrice = totalPrice)
    }
}