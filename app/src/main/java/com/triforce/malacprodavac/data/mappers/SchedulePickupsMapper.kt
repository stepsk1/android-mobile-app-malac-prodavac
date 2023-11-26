package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.schedulePickups.SchedulePickupsEntity
import com.triforce.malacprodavac.domain.model.SchedulePickup

fun SchedulePickupsEntity.toSchedulePickups(): SchedulePickup = SchedulePickup(
    id = id,
    orderId = orderId,
    timeOfDay = timeOfDay,
    date = date,
    accepted = accepted,
    updatedAt = updatedAt,
    createdAt = createdAt
)