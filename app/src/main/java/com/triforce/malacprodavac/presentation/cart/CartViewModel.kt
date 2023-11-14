package com.triforce.malacprodavac.presentation.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.presentation.cart.components.ProductAmount
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
        }
    }
}