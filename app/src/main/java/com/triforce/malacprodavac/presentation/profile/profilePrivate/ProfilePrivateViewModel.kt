package com.triforce.malacprodavac.presentation.profile.profilePrivate

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.services.SessionManager
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.domain.util.compressedFileFromUri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfilePrivateViewModel @Inject constructor(
    private val profile: Profile,
    private val sessionManager: SessionManager
) : ViewModel() {
    var state by mutableStateOf(ProfilePrivateState())

    init {
        me()
        getToken()
    }

    fun onEvent(event: ProfilePrivateEvent) {
        when (event) {
            is ProfilePrivateEvent.Logout -> {
                logout()
            }

            is ProfilePrivateEvent.ChangeProfilePicture -> {
                setProfilePicture(event.context, event.uri)
            }

            ProfilePrivateEvent.Refresh -> {
                me()
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
                            currentUser = result.data,
                            profileImageUrl = "http://softeng.pmf.kg.ac.rs:10010/users/${result.data?.profilePicture?.userId}/medias/${result.data?.profilePicture?.id}",
                            profileImageKey = result.data?.profilePicture?.key
                        )
                    }

                    else -> {}
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

                    else -> {}
                }
            }
        }
    }

    private fun setProfilePicture(context: Context, uri: Uri) {
        viewModelScope.launch {
            val file = compressedFileFromUri(context, uri)
            profile.setProfilePicture(state.currentUser!!.id, file)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }

                        is Resource.Success -> {
                            state =
                                state.copy(
                                    profileImageUrl = "http://softeng.pmf.kg.ac.rs:10010/users/${result.data?.userId}/medias/${result.data?.id}",
                                    profileImageKey = result.data?.key
                                )
                        }
                    }
                }
        }
    }
}