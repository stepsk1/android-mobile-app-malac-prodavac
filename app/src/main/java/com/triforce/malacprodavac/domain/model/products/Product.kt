package com.triforce.malacprodavac.domain.model.products

import com.squareup.moshi.Json
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.model.products.productMedias.ProductMedia
import com.triforce.malacprodavac.domain.model.products.reviews.Review
import com.triforce.malacprodavac.domain.model.shops.Shop
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement

data class Product(
    val id: Int,
    val shopId: Int,
    val categoryId: Int,
    val available: Boolean,
    val price: Double,
    val unitOfMeasurement: UnitOfMeasurement,
    val rating: Double?,
    val availableAtLatitude: Double?,
    val availableAtLongitude: Double?,
    val availableFromHours: Double?,
    val availableTillHours: Double?,
    val currency: Currency,
    val title: String,
    val desc: String,
    val ratingsCount: Double?,
    val availableAt: String?,
    val createdAt: String,
    val updatedAt: String,

    val isFavored: Boolean?,

    val productMedias: List<ProductMedia> = emptyList(),
    val favoriteProducts: List<FavoriteProduct> = emptyList(),
    val shop: Shop?,
    val category: Category?,
    val reviews: List<Review>?,

    @Json(name = "_count")
    val counts: Any?
)
