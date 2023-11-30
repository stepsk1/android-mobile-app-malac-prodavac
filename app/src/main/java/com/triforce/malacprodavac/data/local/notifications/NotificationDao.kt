package com.triforce.malacprodavac.data.local.notifications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotifications(notifications: List<NotificationEntity>)

    @Delete
    suspend fun deleteNotifications(notifications: List<NotificationEntity>)

    @Query("""SELECT * FROM NotificationEntity""")
    suspend fun findNotifications(): List<NotificationEntity>

    @Query("""SELECT * FROM NotificationEntity WHERE id = :id""")
    suspend fun findNotification(id: Int): NotificationEntity
}