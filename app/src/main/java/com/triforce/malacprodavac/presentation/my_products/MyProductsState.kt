package com.triforce.malacprodavac.presentation.my_products

import com.triforce.malacprodavac.domain.model.Product

data class ProductState (
    val id: Int = -1,
    val categoryId: Int? = null,

    val searchQuery: String = "",

    val products: List<Product>? = emptyList(),
    val isLoading: Boolean = false,

    val isOrderSectionVisible: Boolean = false,
    val isFilterSectionVisible: Boolean = false,
    val isStoresSectionVisible: Boolean = false
)