package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.domain.model.Shop

fun ShopEntity.toShop(): Shop = Shop(
    id = id,
    userId = userId,
    openFromDays = openFromDays,
    openTillDays = openTillDays,
    availableAtLatitude = availableAtLatitude,
    availableAtLongitude = availableAtLongitude,
    businessName = businessName,
    openFrom = openFrom,
    openTill = openTill,
    availableAt = availableAt,
    updatedAt = updatedAt,
    createdAt = createdAt,
    user = null,
    products = null
)