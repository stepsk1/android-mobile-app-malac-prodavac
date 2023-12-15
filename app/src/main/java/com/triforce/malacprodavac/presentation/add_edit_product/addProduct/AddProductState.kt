package com.triforce.malacprodavac.presentation.add_edit_product.addProduct

import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement

data class AddProductState(
    val createdProduct: Product? = null,
    val isLoading: Boolean = false,
    val isCreateSuccessful: Boolean = false,
    val categories: List<Category> = emptyList(),

    val errorMessage: String? = null,
    val titleError: String? = null,
    val priceError: String? = null,
    
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