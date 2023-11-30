package com.triforce.malacprodavac.domain.model.notifications

import com.triforce.malacprodavac.domain.model.notifications.notificationPayload.NotificationPayload

data class Notification(
    val id: Int,
    val userId: Int,
    val notificationPayloadId: Int,
    val updatedAt: String,
    val createdAt: String,
    val notificationPayload: NotificationPayload
)