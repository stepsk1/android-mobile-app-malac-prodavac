package com.triforce.malacprodavac.presentation.product

import com.triforce.malacprodavac.domain.model.Product

data class ProductState (

    val product: Product? = null,
    val isLoading: Boolean = false,
    val isBuyed: Boolean = false,
    val isFavorite: Boolean = false
)