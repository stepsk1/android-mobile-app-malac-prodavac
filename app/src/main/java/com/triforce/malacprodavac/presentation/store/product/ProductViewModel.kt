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

    var currentProductId: Int? = null

    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->

            currentProductId = productId
            getProducts(true, productId);
        }

    }

    private fun getProducts(fetchFromRemote: Boolean, productId: Int) {

        viewModelScope.launch {

            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(
                        SingleFilter(
                            "categoryId",
                            FilterOperation.Eq,
                            productId
                        )
                    ), order = null, limit = null, offset = null
                )
            )

            repository.getProducts(productId, fetchFromRemote, query).collect({ result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<Product>) {
                            println(result.data)
                            state = state.copy(products = result.data)
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
            })
        }

    }

}