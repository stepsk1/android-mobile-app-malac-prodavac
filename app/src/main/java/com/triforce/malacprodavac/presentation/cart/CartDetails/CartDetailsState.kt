package com.triforce.malacprodavac.presentation.cart.CartDetails

import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.presentation.cart.components.ProductAmount

data class CartDetailsState(
    val paymentMethod: String = "",
    val address: String = "",
    val listOfProducts: List<ProductAmount> = emptyList(),
    val shippingMethod: String = "",
    val currentUser: User? = null,
    val isLoading: Boolean = false,
    val isSuccesful: Boolean = false
)
