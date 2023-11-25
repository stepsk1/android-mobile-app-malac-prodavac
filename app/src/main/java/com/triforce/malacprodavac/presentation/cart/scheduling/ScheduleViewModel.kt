package com.triforce.malacprodavac.presentation.cart.scheduling

import androidx.lifecycle.ViewModel
import com.triforce.malacprodavac.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: OrderRepository,
) : ViewModel() {

}