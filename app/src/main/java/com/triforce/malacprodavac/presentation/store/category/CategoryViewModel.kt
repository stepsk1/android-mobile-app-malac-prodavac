package com.triforce.malacprodavac.presentation.store.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(

    private val repository: ProductRepository

): ViewModel() {

    var state by mutableStateOf(CategoryState())

    init {
        getProducts(true, null);
    }

    private fun getProducts(fetchFromRemote: Boolean, product: Product?) {

        viewModelScope.launch {

            repository.getProducts(fetchFromRemote).collect({ result ->

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