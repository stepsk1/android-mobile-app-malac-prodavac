package com.triforce.malacprodavac.presentation.FavProducts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.remote.customers.dto.CreateFavoriteProductDto
import com.triforce.malacprodavac.data.remote.orders.dto.UpdateOrderDto
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.orders.OrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: CustomerRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(FavoriteState())
    var listOfProducts: MutableList<FavoriteProduct> = mutableListOf()
    var orderStatus: String = ""

    fun onEvent(event: FavoriteEvent) {
        when(event) {
            is FavoriteEvent.AddFavProduct -> {
                addFavProduct(1, CreateFavoriteProductDto(productId = 6))
            }
        }
    }

    private fun addFavProduct(
        id: Int,
        createFavoriteProductDto: CreateFavoriteProductDto
    )
    {
        viewModelScope.launch {
            repository.insertFavoriteProduct(id, createFavoriteProductDto).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is FavoriteProduct) {
                            state = state.copy(favProduct = result.data)
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