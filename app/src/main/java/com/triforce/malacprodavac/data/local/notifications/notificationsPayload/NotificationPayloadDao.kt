package com.triforce.malacprodavac.data.local.notifications.notificationsPayload

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.triforce.malacprodavac.data.local.notifications.relations.NotificationPayloadWithRelations

@Dao
interface NotificationPayloadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotificationPayloads(notificationPayloads: List<NotificationPayloadEntity>)

    @Delete
    suspend fun deleteNotificationPayloads(notificationPayloads: List<NotificationPayloadEntity>)

    @Query("""SELECT * FROM NotificationPayloadEntity""")
    suspend fun findNotificationPayloads(): List<NotificationPayloadEntity>

    @Query("""SELECT * FROM NotificationPayloadEntity WHERE id = :id""")
    suspend fun findNotificationPayload(id: Int): NotificationPayloadEntity

    @Transaction
    @Query("""SELECT * FROM NotificationPayloadEntity, NotificationEntity WHERE userId = :userId""")
    suspend fun findNotificationPayloadsWithRelations(userId: Int): List<NotificationPayloadWithRelations>
}