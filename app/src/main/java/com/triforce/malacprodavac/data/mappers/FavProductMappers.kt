package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.favoriteProduct.FavouriteProductEntity
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct

fun FavouriteProductEntity.toFavProduct(): FavoriteProduct = FavoriteProduct(
    id = id,
    customerId = customerId,
    productId = productId,
    createdAt = createdAt,
    updatedAt = updatedAt,

    product = null
)