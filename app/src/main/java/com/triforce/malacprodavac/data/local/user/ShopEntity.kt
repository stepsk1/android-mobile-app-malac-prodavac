package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = UserEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId")
)])
data class ShopEntity(
    @PrimaryKey
    val id: Int? = null,
    val businessName: String,
    val upStringdAt: String,
    val createdAt: String,
    val user: UserEntity?,
    val userId: Int
)
