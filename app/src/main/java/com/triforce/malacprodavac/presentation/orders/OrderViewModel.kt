package com.triforce.malacprodavac.presentation.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.product.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val repository: OrderRepository,
    private val repositoryProduct: ProductRepository,
) : ViewModel() {

    var state by mutableStateOf(OrderState())
    var stateProduct by mutableStateOf(ProductState())
    var productList: List<Product> = emptyList()


    init {
        getOrders(true)
        for (product in productList)
            getProduct(true, product.id)
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

    private fun getProduct(fetchFromRemote: Boolean, productId: Int) {

        viewModelScope.launch {
            repositoryProduct.getProduct(productId, fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is Product) {
                            state = state.copy(product = result.data)
                        }
                    }

                    is Resource.Error -> {
                        Unit
                    }

                    is Resource.Loading -> {
                        stateProduct = stateProduct.copy(
                            isLoading = result.isLoading
                        )
                    }
                }
            }
        }
    }
}