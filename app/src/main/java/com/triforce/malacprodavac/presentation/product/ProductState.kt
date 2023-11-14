package com.triforce.malacprodavac.presentation.product

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow

data class ProductState (

    val product: Product? = null,
    val isLoading: Boolean = false,
    val isBuyed: Boolean = false
)