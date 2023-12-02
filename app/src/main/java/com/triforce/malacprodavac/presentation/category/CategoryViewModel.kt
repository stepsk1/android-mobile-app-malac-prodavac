package com.triforce.malacprodavac.presentation.category

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
import com.triforce.malacprodavac.data.services.filter.FilterOrder
import com.triforce.malacprodavac.data.services.filter.SingleFilter
import com.triforce.malacprodavac.data.services.filter.SingleOrder
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.repository.products.ProductRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.highlightSection.HighlightSectionEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(

    private val repository: ProductRepository,
    private val profile: Profile,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    var state by mutableStateOf(CategoryState())

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val debouncePeriod = 500L;
    private var searchJob: Job? = null

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(debouncePeriod)
            currentCategoryId?.let {
                state.user?.let { it1 ->
                    it1.shop?.let { it2 ->
                        getProducts(categoryId = it, myShopId = it2.id, searchText = text)
                    }
                }
            }
        }
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
            me()
            getToken()
        }
        savedStateHandle.get<String>("title")?.let { title ->
            _categoryTitle.value = categoryTitle.value.copy(title = title)
        }
    }

    private fun getToken() {
        profile.getToken().let {
            state = state.copy(token = it)
        }
    }

    private fun me() {
        viewModelScope.launch {
            profile.getMe().collect { result ->
                when (result) {
                    is Resource.Error -> {}

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            user = result.data,
                            profileImageUrl = "http://softeng.pmf.kg.ac.rs:10010/users/${result.data?.profilePicture?.userId}/medias/${result.data?.profilePicture?.id}",
                            profileImageKey = result.data?.profilePicture?.key
                        )

                        state.user?.let {
                            currentCategoryId?.let { it1 ->
                                it.shop?.let { it2 ->
                                    getProducts(myShopId = it2.id, categoryId = it1)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.OrderBy -> {
                currentCategoryId?.let {
                    state.user?.let { it1 ->
                        it1.shop?.let { it2 ->
                            getProducts(
                                myShopId = it2.id,
                                categoryId = it,
                                searchText = searchText.value,
                                orderId = event.order
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getProducts(
        myShopId: Int,
        categoryId: Int,
        searchText: String = "",
        orderId: Int = -1
    ) {
        viewModelScope.launch {
            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(
                        SingleFilter(
                            "categoryId",
                            FilterOperation.Eq,
                            categoryId
                        ),
                        SingleFilter(
                            "title",
                            FilterOperation.IContains,
                            searchText
                        ),
                        SingleFilter(
                            "shopId",
                            FilterOperation.Ne,
                            myShopId
                        )
                    ),
                    order = if (orderId == -1) null else listOf(
                        SingleOrder(
                            "price",
                            if (orderId == 1) FilterOrder.Asc else FilterOrder.Desc
                        )
                    ),
                    limit = null,
                    offset = null
                )
            )
            repository.getProducts(categoryId, true, query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<Product>) {
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