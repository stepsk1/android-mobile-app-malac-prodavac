package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = [ForeignKey(
    entity = UserEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId")
)])
data class ShopEntity(
    @PrimaryKey
    val id: Int? = null,
    val businessName: String,
    val updateAt: LocalDateTime,
    val createAt: LocalDateTime,
    val user: UserEntity?,
    val userId: Int
)
