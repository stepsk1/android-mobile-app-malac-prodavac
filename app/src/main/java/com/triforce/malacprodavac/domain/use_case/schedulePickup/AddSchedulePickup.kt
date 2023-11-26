package com.triforce.malacprodavac.domain.use_case.schedulePickup

import com.triforce.malacprodavac.domain.model.CreateSchedulePickup
import com.triforce.malacprodavac.domain.repository.ScheduledPickupRepository

class AddSchedulePickup (
    private val repository: ScheduledPickupRepository
) {

    suspend operator fun invoke(id: Int, createSchedulePickup: CreateSchedulePickup) {

        repository.insertScheduledPickup(id, createSchedulePickup)
    }
}