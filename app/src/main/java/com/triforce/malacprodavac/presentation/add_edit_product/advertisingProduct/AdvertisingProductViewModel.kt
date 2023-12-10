package com.triforce.malacprodavac.presentation.add_edit_product.advertisingProduct

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.use_case.product.ProductUseCase
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.EditProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdvertisingProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AdvertisingProductState())

    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->
            if (productId != -1) {
                getProduct(true, productId)
            }
        }
    }

    fun onEvent(event: AdvertisingProductEvent) {
        when (event) {
            is AdvertisingProductEvent.TitleChanged -> {
                state = state.copy(product = state.product?.copy(title = event.title))
            }
            is AdvertisingProductEvent.DescChanged -> {
                state = state.copy(product = state.product?.copy(desc = event.desc))
            }
            else -> { }
        }
    }


    private fun getProduct(fetchFromRemote: Boolean, productId: Int) {
        viewModelScope.launch {
            productUseCase.getProductForId(productId, fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(
                                product = result.data,
                                thumbUrl = if (result.data.productMedias!!.isNotEmpty())
                                    "http://softeng.pmf.kg.ac.rs:10010/products/${result.data.productMedias.first().productId}/medias/${result.data.productMedias.first().id}"
                                else null
                            )
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