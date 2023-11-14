package com.triforce.malacprodavac.presentation.category

import com.triforce.malacprodavac.domain.model.Product

data class CategoryState (
    val id: Int = -1,

    val title: String = "",
    val color: String = "",

    val products: List<Product>? = emptyList(),
    val isLoading: Boolean = false,

    val isOrderSectionVisible: Boolean = false,
    val isFilterSectionVisible: Boolean = false,
    val isStoresSectionVisible: Boolean = false

)