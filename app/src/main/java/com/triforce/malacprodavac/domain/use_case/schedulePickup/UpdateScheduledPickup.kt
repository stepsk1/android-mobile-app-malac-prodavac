package com.triforce.malacprodavac.domain.use_case.schedulePickup

import com.triforce.malacprodavac.domain.model.SchedulePickup
import com.triforce.malacprodavac.domain.model.UpdateScheduledPickup
import com.triforce.malacprodavac.domain.repository.ScheduledPickupRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class UpdateScheduledPickup (
    private val repository: ScheduledPickupRepository
) {

    suspend operator fun invoke(
        id: Int,
        scheduledPickupId: Int,
        updateScheduledPickup: UpdateScheduledPickup
    ) : Flow<Resource<SchedulePickup>> {
        return repository.updateScheduledPickup(id, scheduledPickupId, updateScheduledPickup)
    }
}