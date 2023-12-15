package com.triforce.malacprodavac.data.mappers.products

import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.mappers.products.productMedias.toProductMedia
import com.triforce.malacprodavac.data.mappers.products.reviews.toReview
import com.triforce.malacprodavac.data.mappers.toCategory
import com.triforce.malacprodavac.data.mappers.toShop
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement

fun ProductEntity.toProduct(): Product = Product(
    id = id,
    available = available,
    price = price,
    unitOfMeasurement = enumValueOf<UnitOfMeasurement>(unitOfMeasurement),
    rating = rating,
    availableAtLatitude = availableAtLatitude,
    availableAtLongitude = availableAtLongitude,
    availableFromHours = availableFromHours,
    availableTillHours = availableTillHours,
    currency = enumValueOf<Currency>(currency),
    shopId = shopId,
    title = title,
    desc = desc,
    ratingsCount = ratingsCount,
    availableAt = availableAt,
    categoryId = categoryId,
    createdAt = createdAt,
    updatedAt = updatedAt,
    shop = shop?.toShop(),
    productMedia = productMedia?.toProductMedia(),
    category = category?.toCategory(),
    reviews = reviews.map {
        it.toReview()
    },
    isFavored = isFavored,
    counts = null
)

fun Product.toProductEntity(): ProductEntity = ProductEntity(
    id = id,
    available = available,
    price = price,
    unitOfMeasurement = unitOfMeasurement.toString(),
    rating = rating,
    availableAtLatitude = availableAtLatitude,
    availableAtLongitude = availableAtLongitude,
    availableFromHours = availableFromHours,
    availableTillHours = availableTillHours,
    currency = currency.toString(),
    shopId = shopId,
    title = title,
    desc = desc,
    ratingsCount = ratingsCount,
    availableAt = availableAt,
    categoryId = categoryId,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isFavored = isFavored,
)