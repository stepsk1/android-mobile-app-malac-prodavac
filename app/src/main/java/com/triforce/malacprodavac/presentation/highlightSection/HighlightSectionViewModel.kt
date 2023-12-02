package com.triforce.malacprodavac.presentation.highlightSection

import android.util.Log
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
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.repository.products.ProductRepository
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.repository.users.UserRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.profile.profilePublic.ProfilePublicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@HiltViewModel
class HighlightSectionViewModel @Inject constructor(

    private val repositoryShop: ShopRepository,
    private val repositoryProduct: ProductRepository,

    savedStateHandle: SavedStateHandle

) : ViewModel() {

    var state by mutableStateOf(HighlightSectionState())

    private val debouncePeriod = 500L;
    private val searchQueryChannel = Channel<String>(Channel.CONFLATED)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    var currentShopId: Int? = null

    private var searchJob: Job? = null
    fun onSearchTextChange(text: String) {
        _searchText.value = text
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(debouncePeriod)
            currentShopId?.let { getProducts(it, text) }
        }
    }

    init {

        state.copy(isLoading = true)

        savedStateHandle.get<Int>("id")?.let { shopId ->
            if (shopId != -1) {
                currentShopId = shopId
                getShop(shopId)
            }
        }
    }

    private fun getShop(id: Int) {
        viewModelScope.launch {
            repositoryShop.getShop(id, true).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { shop ->
                            state = state.copy(shop = shop)
                            getProducts(shop.id)
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

    private fun getProducts(shopId: Int, searchText: String = "") {
        viewModelScope.launch {

            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(
                        SingleFilter(
                            "shopId",
                            FilterOperation.Eq,
                            shopId
                        ),
                        SingleFilter(
                            "title",
                            FilterOperation.IContains,
                            searchText
                        )
                    ), order = null, limit = null, offset = null
                )
            )

            repositoryProduct.getProducts(shopId, true, query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<Product>) {
                            state = state.copy(products = result.data, isLoading = false)
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