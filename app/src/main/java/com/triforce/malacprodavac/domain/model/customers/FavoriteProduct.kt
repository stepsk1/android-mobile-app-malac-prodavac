package com.triforce.malacprodavac.domain.model.customers

import com.triforce.malacprodavac.domain.model.products.Product

data class FavoriteProduct(
    val id: Int,
    val customerId: Int,
    val productId: Int,
    val updatedAt: String,
    val createdAt: String,

    val product: Product?
)
