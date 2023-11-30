package com.triforce.malacprodavac.data.local.notifications

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.notifications.notificationsPayload.NotificationPayloadEntity

@Entity
data class NotificationEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val notificationPayloadId: Int,
    val updatedAt: String,
    val createdAt: String
) {
    @Ignore
    val notificationPayloadEntity: NotificationPayloadEntity? = null
}
