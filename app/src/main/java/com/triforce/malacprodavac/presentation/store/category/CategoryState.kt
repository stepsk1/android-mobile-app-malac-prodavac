package com.triforce.malacprodavac.presentation.store.category

import com.triforce.malacprodavac.domain.model.Product

data class CategoryState (

    val title: String = "",
    val color1: String = "",
    val color2: String = "",

    val products: List<Product>? = emptyList(),
    val isLoading: Boolean = false,

    val isOrderSectionVisible: Boolean = false,
    val isFilterSectionVisible: Boolean = false,
    val isStoresSectionVisible: Boolean = false

)