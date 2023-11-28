package com.triforce.malacprodavac.presentation.FavShops

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.repository.customers.favoriteShops.dto.CreateFavoriteShopDto
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.profile.profilePublic.FavoriteShopObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteShopViewModel @Inject constructor(
    private val repository: CustomerRepository,
    private val profile: Profile
) : ViewModel(){

    var state by mutableStateOf(FavoriteShopState())
    init {
        me()
    }
    fun onEvent(event: FavoriteShopEvent) {
        when(event) {
            is FavoriteShopEvent.AddFavShop -> {
                addFavShop(state.customerId!!, CreateFavoriteShopDto(shopId = FavoriteShopObject.favoriteShopId))
            }
            is FavoriteShopEvent.GetFavShops -> {
                getFavShops(state.customerId!!, true)
            }
            is FavoriteShopEvent.DeleteFavShop -> {
                deleteFavShop(state.customerId!!, FavoriteShopObject.favoriteShopId)
            }
        }
    }

    private fun getFavShops(
        id: Int,
        fetchFromRemote: Boolean
    ) {
        viewModelScope.launch {
            repository.getFavoriteShops(id, fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<FavoriteShop>) {
                            state = state.copy(favShops = result.data)
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

    private fun addFavShop(
        id: Int,
        createFavoriteShopDto: CreateFavoriteShopDto
    ) {
        viewModelScope.launch {
            repository.insertFavoriteShop(id, createFavoriteShopDto).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is FavoriteShop) {
                            state = state.copy(favShop = result.data)
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

    private fun deleteFavShop(
        id: Int,
        favoriteShopId: Int
    ) {
        viewModelScope.launch {
            repository.deleteFavoriteShop(id, favoriteShopId).collect { result ->
                when (result) {
                    is Resource.Success -> {

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
                            getFavShops(state.customerId!!, true)
                        }
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