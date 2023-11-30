package com.triforce.malacprodavac.data.remote.orders.dto

import com.triforce.malacprodavac.domain.model.CreateSchedulePickup

data class CreateSchedulePickupDto(
    override val timeOfDay: String,
    override val date: String
): CreateSchedulePickup
