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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteShopViewModel @Inject constructor(
    private val repository: CustomerRepository,
    private val profile: Profile
) : ViewModel() {

    var state by mutableStateOf(FavoriteShopState())

    init {
        me()
    }

    fun onEvent(event: FavoriteShopEvent) {
        when (event) {
            is FavoriteShopEvent.AddFavShop -> addFavShop(
                state.customerId!!,
                CreateFavoriteShopDto(shopId = event.shopId)
            )

            is FavoriteShopEvent.GetFavShops -> getFavShops(state.customerId!!)
            is FavoriteShopEvent.DeleteFavShop -> deleteFavShop(
                state.customerId!!,
                event.favoriteShopId
            )
        }
    }

    private fun getFavShops(
        id: Int
    ) {
        viewModelScope.launch {
            repository.getFavoriteShops(id, true).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<FavoriteShop>) {
                            state = state.copy(favShops = result.data)
                        }
                    }

                    is Resource.Error -> handleError()
                    is Resource.Loading -> handleLoading(result.isLoading)
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

                    is Resource.Error -> handleError()
                    is Resource.Loading -> handleLoading(result.isLoading)
                }
            }
        }
    }

    private fun deleteFavShop(
        userId: Int,
        favoriteShopId: Int
    ) {
        viewModelScope.launch {
            repository.deleteFavoriteShop(userId, favoriteShopId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        updateFavoriteShopsAfterDeletion(favoriteShopId)
                    }

                    is Resource.Error -> handleError()
                    is Resource.Loading -> handleLoading(result.isLoading)
                }
            }
        }
    }

    private fun updateFavoriteShopsAfterDeletion(favoriteShopId: Int) {
        val updatedList = state.favShops.toMutableList()
        updatedList.removeIf { it.id == favoriteShopId }
        state = state.copy(favShops = updatedList)
    }

    private fun me() {
        viewModelScope.launch {
            profile.getMe().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data != null) {
                            state = state.copy(
                                customerId = result.data.customer!!.id
                            )
                            getFavShops(state.customerId!!)
                        }
                    }

                    is Resource.Error -> handleError()
                    is Resource.Loading -> handleLoading(result.isLoading)
                }
            }
        }
    }

    private fun handleError() {
    }

    private fun handleLoading(isLoading: Boolean) {
        state = state.copy(isLoading = isLoading)
    }
}