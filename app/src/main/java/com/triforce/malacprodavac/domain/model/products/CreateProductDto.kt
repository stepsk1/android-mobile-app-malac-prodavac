package com.triforce.malacprodavac.domain.model.products

import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement

data class CreateProductDto(
    val unitOfMeasurement: UnitOfMeasurement,
    val currency: Currency,
    val title: String,
    val desc: String,
    val price: Double,
    val categoryId: Int,
    val availableAt: String,
    val availableAtLatitude: Double,
    val availableAtLongitude: Double,
    val availableFromHours: Int,
    val availableTillHours: Int
)