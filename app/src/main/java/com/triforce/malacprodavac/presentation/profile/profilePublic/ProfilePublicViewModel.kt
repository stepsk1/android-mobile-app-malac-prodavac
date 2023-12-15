package com.triforce.malacprodavac.presentation.profile.profilePublic


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.shops.Shop
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePublicViewModel @Inject constructor(

    private val profile: Profile,
    private val repositoryShop: ShopRepository,

    savedStateHandle: SavedStateHandle

) : ViewModel() {
    var state by mutableStateOf(ProfilePublicState())

    init {
        state = state.copy(isLoading = true)

        savedStateHandle.get<Int>("id")?.let { id ->
            if (id != -1) {
                savedStateHandle.get<Int>("role")?.let { role ->
                    if (role == 1) {

                        state = state.copy(role = 1)

                        getShop(id)
                    }
                }
            } else {
                me()
                getToken()
            }
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

                        state.user?.shop?.id?.let { getShop(it) }
                    }
                }
            }
        }
    }

    private fun getShop(shopId: Int) {
        viewModelScope.launch {
            repositoryShop.getShop(fetchFromRemote = true, id = shopId)
                .collectLatest { result ->
                    when (result) {

                        is Resource.Success -> {
                            if (result.data is Shop) {
                                state = state.copy(shop = result.data, isLoading = false)
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