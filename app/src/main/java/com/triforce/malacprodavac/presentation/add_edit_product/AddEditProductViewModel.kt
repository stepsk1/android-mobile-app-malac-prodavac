package com.triforce.malacprodavac.presentation.add_edit_product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.remote.products.dto.CreateProductDto
import com.triforce.malacprodavac.data.remote.products.dto.UpdateProductDto
import com.triforce.malacprodavac.domain.model.CreateProduct
import com.triforce.malacprodavac.domain.model.UpdateProduct
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditProductViewModel @Inject constructor(

    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    var state by mutableStateOf(AddEditProductState())

    init {
        savedStateHandle.get<Int>("productId")?.let { productId ->
            if (productId != -1) {
                getProduct(true, productId)
            }
        }
    }

    fun onEvent(event: AddEditProductEvent) {
        when (event) {
            is AddEditProductEvent.CategoryIdChanged -> {
                state = state.copy(categoryId = event.categoryId)
            }

            is AddEditProductEvent.CurrencyChanged -> {
                state = state.copy(currency = event.currency)
            }

            is AddEditProductEvent.DescChanged -> {
                state = state.copy(desc = event.desc)
            }

            is AddEditProductEvent.PriceChanged -> {
                state = state.copy(price = event.price)
            }

            is AddEditProductEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }

            is AddEditProductEvent.UnitOfMeasurementChanged -> {
                state = state.copy(unitOfMeasurement = event.unitOfMeasurement)
            }

            is AddEditProductEvent.Submit -> {
                if (state.productId == -1) {
                    val createProduct = CreateProductDto(
                        unitOfMeasurement = state.unitOfMeasurement,
                        currency = state.currency,
                        title = state.title,
                        desc = state.desc,
                        price = state.price,
                        categoryId = state.categoryId,
                        availableAt = state.availableAt,
                        availableAtLatitude = state.availableAtLatitude,
                        availableAtLongitude = state.availableAtLongitude,
                        availableFromHours = state.availableFromHours,
                        availableTillHours = state.availableTillHours
                    )
                    insertProduct(createProduct, true)
                } else {
                    val updateProduct = UpdateProductDto(
                        unitOfMeasurement = state.unitOfMeasurement,
                        currency = state.currency,
                        title = state.title,
                        desc = state.desc,
                        price = state.price,
                        categoryId = state.categoryId,
                        availableAt = state.availableAt,
                        availableAtLatitude = state.availableAtLatitude,
                        availableAtLongitude = state.availableAtLongitude,
                        availableFromHours = state.availableFromHours,
                        availableTillHours = state.availableTillHours
                    )
                    updateProduct(state.productId!!, updateProduct, true)
                }
            }
        }
    }

    private fun updateProduct(
        productId: Int,
        updateProduct: UpdateProduct,
        fetchFromRemote: Boolean
    ) {
        viewModelScope.launch {
            repository.updateProduct(productId, updateProduct).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
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

    private fun insertProduct(createProduct: CreateProduct, fetchFromRemote: Boolean) {
        viewModelScope.launch {
            repository.insertProduct(createProduct).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
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

    private fun getProduct(fetchFromRemote: Boolean, productId: Int) {
        viewModelScope.launch {
            repository.getProduct(productId, fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(product = it)
                            state = state.copy(productId = it.id)
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