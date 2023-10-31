package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(foreignKeys = [ForeignKey(
    entity = UserEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId")
)])
data class CourierEntity(
    @PrimaryKey
    val userId: Int,
    val pricePerKilometer: Double,
    val currentLocationLatitude: Double,
    val currentLocationLongitude: Double,
    val currentLocation: String,
    val updateAt: LocalDate,
    val createAt: LocalDate,
    val user: UserEntity?
)
