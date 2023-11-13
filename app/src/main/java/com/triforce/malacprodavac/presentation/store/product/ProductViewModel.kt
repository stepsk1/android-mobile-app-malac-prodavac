package com.triforce.malacprodavac.presentation.store.product

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.data.services.filter.FilterOperation
import com.triforce.malacprodavac.data.services.filter.SingleFilter
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.presentation.store.category.CategoryState
import com.triforce.malacprodavac.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(

    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    var state by mutableStateOf(ProductState())

    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->
            getProduct(true, productId);
        }

    }

    private fun getProduct(fetchFromRemote: Boolean, productId: Int) {

        viewModelScope.launch {
            repository.getProduct(productId, fetchFromRemote).collect{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let{
                            state = state.copy(product = it)
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