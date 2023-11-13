package com.triforce.malacprodavac.data.remote.products.dto

import com.triforce.malacprodavac.domain.model.CreateProduct
import com.triforce.malacprodavac.util.enum.Currency
import com.triforce.malacprodavac.util.enum.UnitOfMeasurement

 data class CreateProductDto(
    override val unitOfMeasurement: UnitOfMeasurement,
    override val currency: Currency,
    override val title: String,
    override val desc: String,
    override val price: Double,
    override val categoryId: Int,
    override val availableAt: String,
    override val availableAtLatitude: Double,
    override val availableAtLongitude: Double,
    override val availableFromHours: Int,
    override val availableTillHours: Int
 ): CreateProduct
