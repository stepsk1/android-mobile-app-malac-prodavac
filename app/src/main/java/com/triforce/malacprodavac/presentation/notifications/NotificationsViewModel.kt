package com.triforce.malacprodavac.presentation.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.domain.repository.notifications.NotificationsRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.orders.OrderedProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: NotificationsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    var state by mutableStateOf(NotificationsState())

    init {
        getNotifications(true)
    }
    private fun getNotifications(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            repository.getNotifications().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            if (result.data is List<Notification>) {
                                state = state.copy(notifications = result.data)
                            }
                            println("NOTIFIKACIJE")
                            println(state.notifications)
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