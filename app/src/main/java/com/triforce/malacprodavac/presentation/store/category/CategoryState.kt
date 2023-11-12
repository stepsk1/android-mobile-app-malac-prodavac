package com.triforce.malacprodavac.presentation.store.category

import com.triforce.malacprodavac.Product

data class CategoryState (

    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,

    val isOrderSectionVisible: Boolean = false,
    val isFilterSectionVisible: Boolean = false,
    val isStoresSectionVisible: Boolean = false

)