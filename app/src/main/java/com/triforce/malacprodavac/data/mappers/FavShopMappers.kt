package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.favouriteShop.FavouriteShopEntity
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop

fun FavouriteShopEntity.toFavShop(): FavoriteShop = FavoriteShop(
    id = id,
    customerId = customerId,
    shopId = shopId,
    createdAt = createdAt,
    updatedAt = updatedAt,

    shop = null
)