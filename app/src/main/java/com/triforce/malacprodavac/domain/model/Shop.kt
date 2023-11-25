package com.triforce.malacprodavac.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.domain.util.enum.DaysOfTheWeek

data class Shop(
    val id: Int,
    val userId: Int,
    val openFromDays: String?,
    val openTillDays: String?,
    val availableAtLatitude: Double?,
    val availableAtLongitude: Double?,
    val businessName: String?,
    val openFrom: String?,
    val openTill: String?,
    val availableAt: String?,
    val updatedAt: String?,
    val createdAt: String?,
    val user: User?,
    val products: List<Product>?
)