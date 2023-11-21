package com.triforce.malacprodavac.presentation.transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.orders.OrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: OrderRepository,
) : ViewModel() {
    var state by mutableStateOf(OrderState())
    var listOfProducts: MutableList<Product> = mutableListOf()
    var product: Product? = null
    private var isCoroutineRunning = false

    init {
        getOrders(true)
    }

    private fun getOrders(fetchFromRemote: Boolean) {
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
}