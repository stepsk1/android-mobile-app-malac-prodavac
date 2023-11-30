package com.triforce.malacprodavac.domain.model.notifications.notificationPayload

import com.squareup.moshi.Json

data class NotificationPayload(
    val id: Int,
    val payload: Payload,
    val updatedAt: String,
    val createdAt: String
)
