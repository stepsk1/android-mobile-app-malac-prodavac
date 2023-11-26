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
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.repository.UserRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.profile.profilePublic.ProfilePublicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlightSectionViewModel @Inject constructor(

    private val profile: Profile,
    private val repositoryShop: ShopRepository,
    private val repositoryUser: UserRepository,
    private val repositoryProduct: ProductRepository,

    savedStateHandle: SavedStateHandle

) : ViewModel() {

    var state by mutableStateOf(ProfilePublicState())

    init {
        savedStateHandle.get<Int>("id")?.let { shopId ->

            Log.d("SHOPID222", shopId.toString())
            if ( shopId != -1 ) {
                getShop(shopId)
            }
        }
    }


    private fun getShop(id: Int) {
        viewModelScope.launch {
            repositoryShop.getShop(id, true).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {shop ->

                            state = state.copy(currentShop = shop)

                            getProducts(true, shop.id)
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

    private fun getProducts(fetchFromRemote: Boolean, shopId: Int, searchText: String = "") {

        viewModelScope.launch {

            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(
                        SingleFilter(
                            "categoryId",
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

            repositoryProduct.getProducts(shopId, fetchFromRemote, query).collect { result ->
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