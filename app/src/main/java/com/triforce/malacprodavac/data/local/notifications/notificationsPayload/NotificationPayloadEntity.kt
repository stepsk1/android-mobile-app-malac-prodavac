package com.triforce.malacprodavac.data.local.notifications.notificationsPayload

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.triforce.malacprodavac.domain.model.notifications.notificationPayload.Payload

@Entity
data class NotificationPayloadEntity(
    @PrimaryKey
    val id: Int,
    val payload: Payload,
    val updatedAt: String,
    val createdAt: String
)
