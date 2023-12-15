package com.triforce.malacprodavac.presentation.FavShops

import com.triforce.malacprodavac.domain.model.customers.FavoriteShop

data class FavoriteShopState(
    val favShops: List<FavoriteShop> = emptyList(),
    val favShop: FavoriteShop? = null,
    val customerId: Int? = null,

    val isLoading: Boolean = false
)
