package com.triforce.malacprodavac.presentation.cart.scheduling

data class ScheduleState(
    val date: String = "",
    val time: String = "",
    val isSuccesful: Boolean = false,
    val isLoading: Boolean = false
)
