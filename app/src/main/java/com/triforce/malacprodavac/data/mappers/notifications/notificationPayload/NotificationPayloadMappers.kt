package com.triforce.malacprodavac.data.mappers.notifications.notificationPayload

import com.triforce.malacprodavac.data.local.notifications.notificationsPayload.NotificationPayloadEntity
import com.triforce.malacprodavac.data.local.notifications.relations.NotificationPayloadWithRelations
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.domain.model.notifications.notificationPayload.NotificationPayload

fun NotificationPayload.toNotificationPayloadEntity(): NotificationPayloadEntity =
    NotificationPayloadEntity(
        id = id,
        payload = payload,
        updatedAt = updatedAt,
        createdAt = createdAt
    )

fun NotificationPayloadEntity.toNotificationPayload(): NotificationPayload =
    NotificationPayload(
        id = id,
        payload = payload,
        updatedAt = updatedAt,
        createdAt = createdAt
    )

fun NotificationPayloadWithRelations.toNotification(): Notification =
    Notification(
        id = notification.id,
        userId = notification.userId,
        notificationPayloadId = notificationPayload.id,
        notificationPayload = notificationPayload.toNotificationPayload(),
        updatedAt = notification.updatedAt,
        createdAt = notification.createdAt
    )