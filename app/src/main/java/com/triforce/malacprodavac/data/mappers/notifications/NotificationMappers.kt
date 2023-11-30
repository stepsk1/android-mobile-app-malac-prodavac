package com.triforce.malacprodavac.data.mappers.notifications

import com.triforce.malacprodavac.data.local.notifications.NotificationEntity
import com.triforce.malacprodavac.data.local.notifications.relations.NotificationPayloadWithRelations
import com.triforce.malacprodavac.data.mappers.notifications.notificationPayload.toNotificationPayloadEntity
import com.triforce.malacprodavac.domain.model.notifications.Notification

fun Notification.toNotificationPayloadWithRelations(): NotificationPayloadWithRelations =
    NotificationPayloadWithRelations(
        notificationPayload = notificationPayload.toNotificationPayloadEntity(),
        notification = NotificationEntity(
            id,
            userId,
            notificationPayloadId,
            updatedAt,
            createdAt,
        )
    )