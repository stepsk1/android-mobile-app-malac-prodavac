package com.triforce.malacprodavac.data.local.notifications.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.triforce.malacprodavac.data.local.notifications.NotificationEntity
import com.triforce.malacprodavac.data.local.notifications.notificationsPayload.NotificationPayloadEntity

data class NotificationPayloadWithRelations(
    @Embedded
    val notificationPayload: NotificationPayloadEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "notificationPayloadId"
    )
    val notification: NotificationEntity
)
