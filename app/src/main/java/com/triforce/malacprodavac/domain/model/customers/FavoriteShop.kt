package com.triforce.malacprodavac.domain.model.customers

import com.triforce.malacprodavac.domain.model.shops.Shop

data class FavoriteShop(
    val id: Int,
    val customerId: Int,
    val shopId: Int,
    val updatedAt: String,
    val createdAt: String,
    val shop: Shop?
)
