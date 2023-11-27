package com.triforce.malacprodavac.presentation.add_edit_product

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.util.enum.Currency
import com.triforce.malacprodavac.util.enum.UnitOfMeasurement

data class AddEditProductState(
    val productId: Int? = -1,
    val product: Product? = null,
    val isLoading: Boolean = false,
    val successful: Boolean = false,


    val unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.KG,
    val currency: Currency = Currency.RSD,
    val title: String = "",
    val desc: String = "",
    val price: Double = 0.0,
    val categoryId: Int = 1,
    val availableAt: String = "",
    val availableAtLatitude: Double = 90.0,
    val availableAtLongitude: Double = 90.0,
    val availableFromHours: Int = 7,
    val availableTillHours: Int = 15
)