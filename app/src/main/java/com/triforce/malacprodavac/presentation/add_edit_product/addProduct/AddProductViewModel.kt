package com.triforce.malacprodavac.presentation.add_edit_product.addProduct

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.products.CreateProductDto
import com.triforce.malacprodavac.domain.model.products.Product
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
            is AddProductEvent.CategoryIdChanged -> updateState { it.copy(categoryId = event.categoryId) }
            is AddProductEvent.CurrencyChanged -> updateState { it.copy(currency = event.currency) }
            is AddProductEvent.DescChanged -> updateState { it.copy(desc = event.desc) }
            is AddProductEvent.PriceChanged -> updateState { it.copy(price = event.price) }
            is AddProductEvent.TitleChanged -> updateState { it.copy(title = event.title) }
            is AddProductEvent.UnitOfMeasurementChanged -> updateState { it.copy(unitOfMeasurement = event.unitOfMeasurement) }
            is AddProductEvent.Submit -> onSubmit()
        }
    }

    private inline fun updateState(update: (AddProductState) -> AddProductState) {
        state = update(state)
    }

    private fun onSubmit() {
        val validationErrors = validateFields()
        if (validationErrors.isEmpty()) {
            val createProduct = createProductDtoFromState()
            insertProduct(createProduct)
        } else {
            updateState { state ->
                state.copy(
                    titleError = validationErrors["title"],
                    priceError = validationErrors["price"]
                )
            }
        }
    }

    private fun validateFields(): Map<String, String?> {
        val errors = mutableMapOf<String, String?>()
        if (state.title.isBlank()) {
            errors["title"] = "Unesite naziv proizvoda!"
        }
        if (state.price <= 0.0) {
            errors["price"] = "Unesite cenu proizvoda!"
        }
        return errors
    }

    private fun createProductDtoFromState(): CreateProductDto {
        return CreateProductDto(
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
    }

    private fun insertProduct(createProductDto: CreateProductDto) {
        viewModelScope.launch {
            productUseCase.addProduct(createProductDto).collect { result ->
                when (result) {
                    is Resource.Success -> handleInsertProductSuccess(result.data, result.message)
                    is Resource.Error -> handleError()
                    is Resource.Loading -> handleLoading(result.isLoading)
                }
            }
        }
    }

    private fun handleInsertProductSuccess(createdProduct: Product?, errorMessage: String?) {
        updateState { state ->
            state.copy(
                createdProduct = createdProduct,
                isCreateSuccessful = true,
                errorMessage = errorMessage
            )
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoryUseCases.getCategories().collect { result ->
                when (result) {
                    is Resource.Success -> result.data?.let { handleGetCategoriesSuccess(it) }
                    is Resource.Error -> handleError()
                    is Resource.Loading -> handleLoading(result.isLoading)
                }
            }
        }
    }

    private fun handleGetCategoriesSuccess(categories: List<Category>) {
        updateState { state ->
            state.copy(categories = categories)
        }
    }

    private fun handleError() {
    }

    private fun handleLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }
}