package com.triforce.malacprodavac.presentation.store.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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

    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    var state by mutableStateOf(CategoryState())

    init {
        savedStateHandle.get<Int>("categoryId")?.let { categoryId ->
            getProducts(true, categoryId);
        }
    }

    private fun getProducts(fetchFromRemote: Boolean, categoryId: Int) {

        viewModelScope.launch {

            repository.getProducts(categoryId, fetchFromRemote).collect({ result ->

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