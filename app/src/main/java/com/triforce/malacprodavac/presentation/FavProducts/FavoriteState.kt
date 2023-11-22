package com.triforce.malacprodavac.presentation.FavProducts

import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct

data class FavoriteState(
    val favProducts: List<FavoriteProduct> = emptyList(),
    val isLoading: Boolean = false,
    val favProduct: FavoriteProduct? = null
)
