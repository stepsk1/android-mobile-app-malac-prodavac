package com.triforce.malacprodavac.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Shop(
    val id: Int,
    val userId: Int,
    val businessName: String?,
    val updatedAt: String,
    val createdAt: String,

    val user: User?,
    val products: List<Product>?
)