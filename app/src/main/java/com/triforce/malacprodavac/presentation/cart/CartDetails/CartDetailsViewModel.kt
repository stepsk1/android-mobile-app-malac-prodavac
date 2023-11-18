package com.triforce.malacprodavac.presentation.cart.CartDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.remote.orders.dto.CreateOrderDto
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartDetailsViewModel @Inject constructor(
    private val repository: OrderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(CartDetailsState())

    fun onEvent(event: CartDetailsEvent) {
        when (event) {
            CartDetailsEvent.order -> {
                for (buyedProduct in BuyedProducts.listOfBuyedProducts){
                    buyProducts(
                        true,
                        buyedProduct.product.id,
                        buyedProduct.amount,
                        BuyedProducts.deliveryMethod,
                        BuyedProducts.paymentMethod
                    )
                }
            }
        }
    }

    private fun buyProducts(fetchFromRemote: Boolean,
                            productId: Int,
                            quantity: Int,
                            deliveryMethod: DeliveryMethod,
                            paymentMethod: PaymentMethod
                            ) {
        viewModelScope.launch {
            repository.insertOrder(createOrder = CreateOrderDto(
                deliveryMethod = deliveryMethod,
                paymentMethod = paymentMethod,
                productId = productId,
                quantity = quantity
            )).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(isSuccesful = true)
                        }
                    }

                    is Resource.Error -> {
                        Unit
                    }

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }
                }
            }
        }
    }
}
