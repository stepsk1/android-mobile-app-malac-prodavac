package com.triforce.malacprodavac.presentation.FavProducts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.remote.customers.dto.CreateFavoriteProductDto
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.product.FavouriteProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: CustomerRepository,
    private val profile: Profile
) : ViewModel() {

    var state by mutableStateOf(FavoriteState())
    var listOfProducts: MutableList<FavoriteProduct> = mutableListOf()
    var orderStatus: String = ""

    init {
        me()
//        getFavProducts(state.customerId!!, true)
    }

    fun onEvent(event: FavoriteEvent) {
        when(event) {
            is FavoriteEvent.AddFavProduct -> {
                addFavProduct(state.customerId!!, CreateFavoriteProductDto(productId = FavouriteProduct.favouriteProductId))
            }
//            is FavoriteEvent.GetUser -> {
//                me()
//            }
            is FavoriteEvent.GetFavProducts -> {
                getFavProducts(state.customerId!!, true)
            }

            is FavoriteEvent.DeleteFavProduct -> {
                deleteFavProduct(state.customerId!!, FavouriteProduct.favouriteProductId)
            }
        }
    }

    private fun getFavProducts(
        id: Int,
        fetchFromRemote: Boolean
    )
    {
        viewModelScope.launch {
            repository.getFavoriteProducts(id, fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<FavoriteProduct>) {
                            state = state.copy(favProducts = result.data)
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


    private fun addFavProduct(
        id: Int,
        createFavoriteProductDto: CreateFavoriteProductDto
    )
    {
        viewModelScope.launch {
            repository.insertFavoriteProduct(id, createFavoriteProductDto).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is FavoriteProduct) {
                            state = state.copy(favProduct = result.data)
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


    private fun deleteFavProduct(
        id: Int,
        favoriteProductId: Int
    )
    {
        viewModelScope.launch {
            repository.deleteFavoriteProduct(id, favoriteProductId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is FavoriteProduct) {

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

    private fun me(){
        viewModelScope.launch {
            profile.getMe().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if(result.data != null) {
                            state = state.copy(
                                customerId = result.data!!.customer!!.id
                            )
                            getFavProducts(state.customerId!!, true)
                        }
                        println("REZULTAT")
                        println(state.customerId)
                    }
                    is Resource.Error -> Unit

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    else -> {}
                }
            }
        }
    }
}