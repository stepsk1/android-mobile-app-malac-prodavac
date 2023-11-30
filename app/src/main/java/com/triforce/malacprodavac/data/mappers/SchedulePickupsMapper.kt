package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.scheduledPickup.ScheduledPickupEntity
import com.triforce.malacprodavac.domain.model.SchedulePickup

fun ScheduledPickupEntity.toSchedulePickups(): SchedulePickup = SchedulePickup(
    id = id,
    orderId = orderId,
    timeOfDay = timeOfDay,
    date = date,
    accepted = accepted,
    updatedAt = updatedAt,
    createdAt = createdAt
)