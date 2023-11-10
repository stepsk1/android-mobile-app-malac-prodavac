package com.triforce.malacprodavac.domain.model

import androidx.room.PrimaryKey

data class Product(
    val id: Int,
    val available: Boolean,
    val price: Double,
    val unitOfMeasurement: String, //Convert to enum
    val rating: Double?,
    val availableAtLatitude: Double?,
    val availableAtLongitude: Double?,
    val availableFromHours: Double?,
    val availableTillHours: Double?,
    val currency: String, //Convert to enum
    val shopId: Int,
    val title: String,
    val desc: String,
    val ratingsCount: Double?,
    val availableAt: String?,
    val categoryId: Int,
    val createdAt: String?,
    val updatedAt: String?
)
