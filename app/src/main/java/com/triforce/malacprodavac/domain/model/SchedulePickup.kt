package com.triforce.malacprodavac.domain.model

data class SchedulePickup(
    val id: Int,
    val orderId: Int,
    val timeOfDay: String,
    val date: String,
    val accepted: Boolean,
    val updatedAt: String,
    val createdAt: String
)
