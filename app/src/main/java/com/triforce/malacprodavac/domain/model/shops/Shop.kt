package com.triforce.malacprodavac.domain.model.shops

import com.squareup.moshi.Json
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.model.products.Product

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

    val isFavored: Boolean?,

    val user: User?,
    val products: List<Product>?,

    @Json(name = "_count")
    val counts: Any?
)