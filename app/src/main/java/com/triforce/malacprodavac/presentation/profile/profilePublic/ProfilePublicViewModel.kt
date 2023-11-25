package com.triforce.malacprodavac.presentation.profile.profilePublic


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.repository.UserRepository
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePublicViewModel @Inject constructor(

    private val profile: Profile,
    private val repository: UserRepository,
    savedStateHandle: SavedStateHandle

) : ViewModel() {
    var state by mutableStateOf(ProfilePublicState())


    var currentUserId: Int? = null

    init {
        me()
        getToken()

        savedStateHandle.get<Int>("id")?.let { id ->
            if ( id != -1 ) {
                currentUserId = id
                Log.d("CURRENT_USER_ID_2", currentUserId.toString())
                getUser(id)
                Log.d("CURRENT_USER_ID_2", state.currentUser.toString())
            }
        }
    }

    fun onEvent(event: ProfilePublicEvent) {
        viewModelScope.launch {
            when (event) {
                is ProfilePublicEvent.Logout -> {
                    logout()
                }
            }
        }
    }


    fun isLoggedIn(): Boolean {
        return state.isLoggedIn
    }

    private fun getToken() {
        profile.getToken().let {
            Log.d("TOKEN", it.toString())
            state = state.copy(token = it)
        }
    }

    private fun me() {
        viewModelScope.launch {
            profile.getMe().collect { result ->
                when (result) {
                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }

                    is Resource.Success -> {
                        state = state.copy(
                            currentUser = result.data
                        )
                    }
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            profile.logout().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(isLoggedIn = false)
                    }

                    is Resource.Error -> TODO()
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }

    private fun getUser(id: Int) {

        viewModelScope.launch {
            repository.getUser(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(currentUser = it)
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