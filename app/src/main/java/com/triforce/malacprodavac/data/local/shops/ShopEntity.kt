package com.triforce.malacprodavac.data.local.shops

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.util.enum.DaysOfTheWeek

@Entity(
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId")
    )]
)
data class ShopEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,

    val openFromDays: DaysOfTheWeek,
    val openTillDays: DaysOfTheWeek,

    val availableAtLatitude: Double,
    val availableAtLongitude: Double,

    val businessName: String,

    val openFrom: String,
    val openTill: String,

    val availableAt: String,

    val updatedAt: String,
    val createdAt: String,

    val user: User,
    val products: List<Product>
)
