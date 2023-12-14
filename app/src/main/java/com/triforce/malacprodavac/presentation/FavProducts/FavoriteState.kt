package com.triforce.malacprodavac.presentation.FavProducts

import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct

data class FavoriteState(
    val favProducts: List<FavoriteProduct> = emptyList(),
    val favProduct: FavoriteProduct? = null,
    val customerId: Int? = null,

    val isLoading: Boolean = false
)
