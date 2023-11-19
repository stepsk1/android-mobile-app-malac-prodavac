package com.triforce.malacprodavac.presentation.add_edit_product

import com.triforce.malacprodavac.util.enum.Currency
import com.triforce.malacprodavac.util.enum.UnitOfMeasurement

data class AddEditProductState(
    val productId: Int? = null,

    val unitOfMeasurement: UnitOfMeasurement? = null,
    val currency: Currency? = null,
    val title: String? = "",
    val desc: String? = "",
    val price: Double? = null,
    val categoryId: Int? = null,
    val availableAt: String? = "",
    val availableAtLatitude: Double? = null,
    val availableAtLongitude: Double? = null,
    val availableFromHours: Int? = null,
    val availableTillHours: Int? = null
)