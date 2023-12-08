package com.triforce.malacprodavac.presentation.add_edit_product.addProduct

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.products.CreateProductDto
import com.triforce.malacprodavac.domain.use_case.category.CategoryUseCase
import com.triforce.malacprodavac.domain.use_case.product.ProductUseCase
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
data class AddProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val categoryUseCases: CategoryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(AddProductState())

    init {
        getCategories()
    }

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.CategoryIdChanged -> {
                state = state.copy(categoryId = event.categoryId)
            }

            is AddProductEvent.CurrencyChanged -> {
                state = state.copy(currency = event.currency)
            }

            is AddProductEvent.DescChanged -> {
                state = state.copy(desc = event.desc)
            }

            is AddProductEvent.PriceChanged -> {
                state = state.copy(price = event.price)
            }

            is AddProductEvent.TitleChanged -> {
                state = state.copy(title = event.title)
            }

            is AddProductEvent.UnitOfMeasurementChanged -> {
                state = state.copy(unitOfMeasurement = event.unitOfMeasurement)
            }

            is AddProductEvent.Submit -> {
                state = if (state.title.isBlank()) {
                    state.copy(titleError = "Unesite naziv!")
                } else {
                    state.copy(titleError = null)
                }
                state = if (state.price <= 0.0) {
                    state.copy(priceError = "Unesite cenu!")
                } else {
                    state.copy(priceError = null)
                }
                if (state.titleError != null || state.priceError != null)
                    return
                state = state.copy(titleError = null, priceError = null)
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
                insertProduct(createProduct)
            }
        }
    }

    private fun insertProduct(createProductDto: CreateProductDto) {
        viewModelScope.launch {
            productUseCase.addProduct(createProductDto).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(createdProduct = it, isCreateSuccessful = true)
                        }
                        result.message?.let {
                            state = state.copy(errorMessage = result.message)
                        }
                    }

                    is Resource.Error -> {
                        Unit
                    }

                    is Resource.Loading -> {
                    }
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoryUseCases.getCategories().collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(categories = it)
                        }
                    }
                }
            }
        }
    }
}
