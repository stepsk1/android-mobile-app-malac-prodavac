package com.triforce.malacprodavac.presentation.FavShops

import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop

data class FavoriteShopState(
    val favShops: List<FavoriteShop> = emptyList(),
    val isLoading: Boolean = false,
    val favShop: FavoriteShop? = null,
    val customerId: Int? = null
)
