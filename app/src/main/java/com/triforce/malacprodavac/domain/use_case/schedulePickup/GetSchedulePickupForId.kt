package com.triforce.malacprodavac.domain.use_case.schedulePickup

import com.triforce.malacprodavac.domain.model.SchedulePickup
import com.triforce.malacprodavac.domain.repository.ScheduledPickupRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.QueryMap

class GetSchedulePickupForId (
    private val repository: ScheduledPickupRepository
) {
    suspend operator fun invoke(
        id: Int,
        scheduledPickupId: Int,
        fetchFromRemote: Boolean,
        @QueryMap() query: MutableMap<String, String>
    ) : Flow<Resource<SchedulePickup>> {
        return repository.getScheduledPickup(id,scheduledPickupId, fetchFromRemote)
    }
}