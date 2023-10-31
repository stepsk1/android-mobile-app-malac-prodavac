package com.triforce.malacprodavac.domain.model

data class Customer(
    val id: Int,
    val createdAt: String,
    val favoriteProducts: List<String>,
    val favoriteShops: List<String>,
    val upStringdAt: String,
    val userId: Int
)