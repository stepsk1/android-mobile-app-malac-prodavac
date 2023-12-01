package com.triforce.malacprodavac.presentation.product

import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.domain.model.products.reviews.Review

data class ProductState(
    val product: Product? = null,
    val reviews: List<Review>? = emptyList(),
    val createReviewError: String? = null,
    val isLoading: Boolean = false,
    val isBuyed: Boolean = false,
    val isFavorite: Boolean = false
)