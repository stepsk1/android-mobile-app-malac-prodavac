package com.triforce.malacprodavac.presentation.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.use_case.notifications.NotificationsUseCase
import com.triforce.malacprodavac.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val notificationsUseCase: NotificationsUseCase
) : ViewModel() {

    var state by mutableStateOf(NotificationsState())

    init {
        getNotifications(true)
    }

    private fun getNotifications(fetchFromRemote: Boolean, page: Int = 1, perPage: Int = 20) {
        viewModelScope.launch {
            notificationsUseCase.getNotifications(page, perPage)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(
                                    notifications = state.notifications + result.data.data,
                                    currentPage = result.data.meta.currentPage,
                                    isLastPage = result.data.meta.isLastPage
                                )
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

    fun loadNextPage() {
        getNotifications(true, state.currentPage + 1, state.perPage)
    }
}