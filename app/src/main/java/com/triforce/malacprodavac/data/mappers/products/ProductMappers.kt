package com.triforce.malacprodavac.data.mappers.products

import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.data.mappers.products.productMedias.toProductMedia
import com.triforce.malacprodavac.data.mappers.toCategory
import com.triforce.malacprodavac.data.mappers.toShop
import com.triforce.malacprodavac.domain.model.Product

fun ProductEntity.toProduct(): Product = Product(
    id = id,
    available = available,
    price = price,
    unitOfMeasurement = unitOfMeasurement,
    rating = rating,
    availableAtLatitude = availableAtLatitude,
    availableAtLongitude = availableAtLongitude,
    availableFromHours = availableFromHours,
    availableTillHours = availableTillHours,
    currency = currency,
    shopId = shopId,
    title = title,
    desc = desc,
    ratingsCount = ratingsCount,
    availableAt = availableAt,
    categoryId = categoryId,
    createdAt = createdAt,
    updatedAt = updatedAt,
    shop = shop?.toShop(),
    productMedias = productImages.map {
        it.toProductMedia()
    },
    category = category?.toCategory()
)

fun Product.toProductEntity(): ProductEntity = ProductEntity(
    id = id,
    available = available,
    price = price,
    unitOfMeasurement = unitOfMeasurement,
    rating = rating,
    availableAtLatitude = availableAtLatitude,
    availableAtLongitude = availableAtLongitude,
    availableFromHours = availableFromHours,
    availableTillHours = availableTillHours,
    currency = currency,
    shopId = shopId,
    title = title,
    desc = desc,
    ratingsCount = ratingsCount,
    availableAt = availableAt,
    categoryId = categoryId,
    createdAt = createdAt,
    updatedAt = updatedAt,
)