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
    var id: Int,
    var userId: Int,
    var openFromDays: String?,
    var openTillDays: String?,
    var availableAtLatitude: Double?,
    var availableAtLongitude: Double?,
    var businessName: String?,
    var openFrom: String?,
    var openTill: String?,
    var availableAt: String?,
    var updatedAt: String?,
    var createdAt: String?
)