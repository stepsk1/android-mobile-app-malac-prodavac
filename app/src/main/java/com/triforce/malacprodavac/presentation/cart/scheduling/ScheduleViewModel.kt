package com.triforce.malacprodavac.presentation.cart.scheduling

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.triforce.malacprodavac.domain.repository.ScheduledPickupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: ScheduledPickupRepository
) : ViewModel() {

    var state by mutableStateOf(ScheduleState())

}