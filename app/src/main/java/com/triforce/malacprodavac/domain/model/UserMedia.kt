package com.triforce.malacprodavac.domain.model

import java.util.Date

data class UserMedia(
    val id: Int,
    val userId: Int,
    val mimetype: String,
    val key: String,
    val originalName: String,
    val name: String?,
    val updatedAt: Date,
    val createdAt: Date
)
