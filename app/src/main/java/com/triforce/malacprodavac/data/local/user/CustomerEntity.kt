package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = UserEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId")
)])
data class CustomerEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val upStringdAt: String,
    val createdAt: String,
    val favoriteShops: List<Int>,
    val favoriteProducts: List<Int>,
    val user: UserEntity?
)
