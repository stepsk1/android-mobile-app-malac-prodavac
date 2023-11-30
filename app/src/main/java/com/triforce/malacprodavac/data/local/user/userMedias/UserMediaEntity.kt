package com.triforce.malacprodavac.data.local.user.userMedias

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserMediaEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val mimetype: String,
    val key: String,
    val originalName: String,
    val name: String?,
    val updatedAt: String,
    val createdAt: String,
)
