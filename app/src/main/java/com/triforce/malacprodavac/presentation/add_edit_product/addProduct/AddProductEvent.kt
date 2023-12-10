package com.triforce.malacprodavac.presentation.add_edit_product.addProduct

import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement

sealed class AddProductEvent {
    data class UnitOfMeasurementChanged(val unitOfMeasurement: UnitOfMeasurement) :
        AddProductEvent()

    data class CurrencyChanged(val currency: Currency) : AddProductEvent()
    data class TitleChanged(val title: String) : AddProductEvent()
    data class DescChanged(val desc: String) : AddProductEvent()
    data class PriceChanged(val price: Double) : AddProductEvent()
    data class CategoryIdChanged(val categoryId: Int) : AddProductEvent()
    data object Submit : AddProductEvent()

}