package com.triforce.malacprodavac.domain.model

data class Product(
    val id: Int,
    val shopId: Int,
    val categoryId: Int,
    val available: Boolean,
    val price: Double,
    val unitOfMeasurement: String, //Convert to enum
    val rating: Double?,
    val availableAtLatitude: Double?,
    val availableAtLongitude: Double?,
    val availableFromHours: Double?,
    val availableTillHours: Double?,
    val currency: String, //Convert to enum
    val title: String,
    val desc: String,
    val ratingsCount: Double?,
    val availableAt: String?,
    val createdAt: String,
    val updatedAt: String,

    val shop: Shop?,
    val category: Category?
){
    fun doesMatchSearchQuery(query: String): Boolean{

        return title.contains(query, ignoreCase = true)

    }
}
