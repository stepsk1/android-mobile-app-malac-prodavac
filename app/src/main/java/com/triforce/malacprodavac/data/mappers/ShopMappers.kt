package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.util.enum.DaysOfTheWeek

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

    user = user,
    products = products
)