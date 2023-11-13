package com.triforce.malacprodavac.presentation.store.product

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow

data class ProductState (

    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
)