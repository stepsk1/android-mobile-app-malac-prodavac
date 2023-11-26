package com.triforce.malacprodavac.presentation.profile.profilePublic


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
import com.triforce.malacprodavac.domain.repository.users.UserRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePublicViewModel @Inject constructor(

    private val profile: Profile,
    private val repositoryShop: ShopRepository,
    private val repositoryUser: UserRepository,
    private val repositoryProduct: ProductRepository,

    savedStateHandle: SavedStateHandle

) : ViewModel() {
    var state by mutableStateOf(ProfilePublicState())

    init {

        savedStateHandle.get<Int>("id")?.let { id ->
            if ( id != -1 ) {
                savedStateHandle.get<Int>("role")?.let { role ->
                    if ( role == 1 ){
                        getShop(id)
                    }
                }
            }
        }
    }

    fun onEvent(event: ProfilePublicEvent) {

    }


    private fun getShop(id: Int) {
        viewModelScope.launch {
            repositoryShop.getShop(id, true).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {shop ->

                            state = state.copy(currentShop = shop)

                            getUser(shop.userId)

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
    private fun getUser(id: Int) {
        viewModelScope.launch {
            repositoryUser.getUser(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            state = state.copy(currentUser = user)
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