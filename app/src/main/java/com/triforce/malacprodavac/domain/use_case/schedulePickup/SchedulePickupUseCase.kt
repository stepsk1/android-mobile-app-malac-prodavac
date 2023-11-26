package com.triforce.malacprodavac.domain.use_case.schedulePickup

data class SchedulePickupUseCase(
    val getAllScheduledPickups: GetAllScheduledPickups,
    val getSchedulePickupForId: GetSchedulePickupForId,
    val addSchedulePickup: AddSchedulePickup,
    val updateScheduledPickup: UpdateScheduledPickup
)
