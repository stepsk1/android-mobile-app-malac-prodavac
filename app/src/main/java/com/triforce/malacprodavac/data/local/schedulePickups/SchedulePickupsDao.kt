package com.triforce.malacprodavac.data.local.schedulePickups

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.triforce.malacprodavac.data.local.order.OrderEntity


@Dao
interface SchedulePickupsDao {

    @Query("SELECT * FROM SchedulePickupsEntity")
    suspend fun getAllSchedules(): List<SchedulePickupsEntity>

    @Query("""SELECT * FROM SchedulePickupsEntity
        WHERE id = :id""")
    suspend fun getScheduleForId(id: Int): List<SchedulePickupsEntity>

    @Query("DELETE FROM SchedulePickupsEntity")
    suspend fun clearSchedule()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedulePickups: List<SchedulePickupsEntity>)
}