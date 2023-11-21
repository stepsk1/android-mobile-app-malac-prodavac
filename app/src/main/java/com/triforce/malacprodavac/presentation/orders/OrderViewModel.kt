package com.triforce.malacprodavac.presentation.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.remote.orders.dto.UpdateOrderDto
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository,
    private val repositoryProduct: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(OrderState())
    var listOfProducts: MutableList<Product> = mutableListOf()
    var product: Product? = null
    private var isCoroutineRunning = false
    var orderStatus: String = ""

    fun onEvent(event: OrderEvent) {
        when(event) {
            is OrderEvent.changeStatusOfOrder -> {
                if(state.order?.orderStatus == "Ordered")
                    orderStatus = "Potvrđeno"
                else if(state.order?.orderStatus == "Packaged")
                    orderStatus = "U isporuci"
                val updateOrderDto = UpdateOrderDto(
                    orderStatus = orderStatus,
                    accepted = true
                )

                updateOrderStatus(1, updateOrderDto)
                println("PROMENAAAAA")
                println(state.order?.orderStatus)
            }
        }
    }

    init {
        getAcceptedOrders(true)

    }

    private fun getAcceptedOrders(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            repository.getOrders(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            if (result.data is List<Order>) {
                                state = state.copy(orders = result.data)
                            }
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

    public fun getProduct(fetchFromRemote: Boolean, productId: Int): Product? {
        if (!isCoroutineRunning){
            viewModelScope.launch {
                repositoryProduct.getProduct(productId, fetchFromRemote).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            if (result.data is Product) {
                                listOfProducts.add(result.data)
                                state = state.copy(product = result.data)
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
            isCoroutineRunning = true
        }
        return state.product
    }

    private fun updateOrderStatus(
            orderId: Int,
            updateOrderDto: UpdateOrderDto)
    {
        viewModelScope.launch {
            repository.updateOrder(orderId, updateOrderDto).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is Order) {
                            state = state.copy(order = result.data)
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