package com.triforce.malacprodavac.domain.model

data class Customer(
    val id: Int,
    val createdAt: String,
    val favoriteProducts: List<String>,
    val favoriteShops: List<String>,
    val updatedAt: String,
    val userId: Int
)