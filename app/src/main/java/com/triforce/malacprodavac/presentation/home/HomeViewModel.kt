package com.triforce.malacprodavac.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.launchdarkly.eventsource.MessageEvent
import com.triforce.malacprodavac.SSEClient
import com.triforce.malacprodavac.SSEHandler
import com.triforce.malacprodavac.domain.use_case.profile.Profile
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profile: Profile,
    private val sseClient: SSEClient
) : ViewModel(), SSEHandler {
    var state by mutableStateOf(HomeScreenState())

    init {
        state.copy(isLoading = true)
        me()
        getToken()
        viewModelScope.launch(Dispatchers.IO) {
            sseClient.initSse(this@HomeViewModel) { error ->
                Log.e("ERROR_CB", error.message.toString())
                error.printStackTrace()
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Refresh -> {
                me()
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return profile.isAuthenticated.invoke()
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

    override fun onSSEConnectionOpened() {
        Log.i("HOME VM", "CONN OPEN!")
    }

    override fun onSSEConnectionClosed() {
        Log.i("HOME VM", "CONN CLOSED!")
    }

    override fun onSSEEventReceived(event: String, messageEvent: MessageEvent) {
        Log.i("HOME VM", "NEW EVENT RECEIVED!")
    }

    override fun onSSEError(t: Throwable) {
        Log.i("HOME VM", "CONN ERROR!")
        t.printStackTrace()
    }

    fun disconnect() {
        sseClient.disconnect()
    }
}