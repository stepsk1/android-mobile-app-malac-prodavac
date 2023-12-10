package com.triforce.malacprodavac.presentation.cart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.repository.cart.CartRepository
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.shops.Shop
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.repository.products.ProductRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.cart.components.CartItem
import com.triforce.malacprodavac.presentation.cart.components.ProductAmount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(

) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    init {
        fetchCartItems()
    }

    private fun fetchCartItems() {
        _cartItems.value = CartRepository.getCartItems()
    }

    fun addToCart(product: Product, shop: Shop?) {
        val existingItem = _cartItems.value.find { it.product.id == product.id }

        if (existingItem != null) {
            existingItem.quantity++
        } else {
            val newItem = CartItem(product, shop)
            CartRepository.addItemToCart(newItem)
        }

        fetchCartItems()
    }

    fun removeFromCart(item: CartItem) {
        CartRepository.removeItemFromCart(item)
        fetchCartItems()
    }

    fun clearCart() {
        CartRepository.clearCart()
        fetchCartItems()
    }
}
