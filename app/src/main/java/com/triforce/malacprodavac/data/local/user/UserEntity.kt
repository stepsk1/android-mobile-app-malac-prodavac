package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
    val profilePicUrl: String,
    @PrimaryKey val userId: Int? = null
)