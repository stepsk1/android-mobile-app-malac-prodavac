package com.triforce.malacprodavac.domain.model.notifications.notificationPayload

data class Data(
    val title: String,
    val shops: List<String>? = null
)