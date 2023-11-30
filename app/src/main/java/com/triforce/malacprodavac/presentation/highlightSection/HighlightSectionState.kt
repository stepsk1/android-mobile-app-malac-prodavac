package com.triforce.malacprodavac.presentation.highlightSection

import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.shops.Shop
import com.triforce.malacprodavac.domain.model.User

data class HighlightSectionState(
    val isLoading: Boolean = false,
    val currentUser: User? = null,
    val currentShop: Shop? = null,
    val products: List<Product>? = emptyList(),
)