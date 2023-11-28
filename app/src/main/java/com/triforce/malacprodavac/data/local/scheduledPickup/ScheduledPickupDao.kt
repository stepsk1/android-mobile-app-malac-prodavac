package com.triforce.malacprodavac.data.local.scheduledPickup

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ScheduledPickupDao {

    @Query("SELECT * FROM ScheduledPickupEntity")
    suspend fun getAllSchedules(): List<ScheduledPickupEntity>

    @Query(
        """SELECT * FROM ScheduledPickupEntity
        WHERE id = :id"""
    )
    suspend fun getScheduleForId(id: Int): List<ScheduledPickupEntity>

    @Query("DELETE FROM ScheduledPickupEntity")
    suspend fun clearSchedule()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedulePickups: List<ScheduledPickupEntity>)
}