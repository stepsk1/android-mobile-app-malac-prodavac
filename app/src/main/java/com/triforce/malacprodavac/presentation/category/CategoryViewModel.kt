package com.triforce.malacprodavac.presentation.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.data.services.filter.FilterOperation
import com.triforce.malacprodavac.data.services.filter.SingleFilter
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(

    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    var state by mutableStateOf(CategoryState())

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _products = MutableStateFlow(state.products)

    @OptIn(FlowPreview::class)
    val products = searchText
        .debounce(500L) // dodaje delay, da ne bi slao api zahteve stalno, tako da kada prestane da kuca, onda šalje
        .onEach { _isSearching.update { true } }
        .combine(_products) { text, products ->

            if(text.isBlank()) {
                products
            } else {
                products?.filter {

                    delay(2000L) // simulation

                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _products.value
        ) // kešira, čuva poslednje podatke

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    private val _categoryTitle = mutableStateOf(
        CategoryState(

            title = "Category title..."

        )
    )
    val categoryTitle: State<CategoryState> = _categoryTitle

    var currentCategoryId: Int? = null

    init {
        savedStateHandle.get<Int>("categoryId")?.let { categoryId ->

            currentCategoryId = categoryId

            getProducts(true, categoryId)
        }

        savedStateHandle.get<String>("title")?.let { title ->
            _categoryTitle.value = categoryTitle.value.copy(title = title)
        }
    }

    private fun getProducts(fetchFromRemote: Boolean, categoryId: Int) {

        viewModelScope.launch {

            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(
                        SingleFilter(
                            "categoryId",
                            FilterOperation.Eq,
                            categoryId
                        )
                    ), order = null, limit = null, offset = null
                )
            )

            repository.getProducts(categoryId, fetchFromRemote, query).collect { result ->
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
            }
        }
    }
}