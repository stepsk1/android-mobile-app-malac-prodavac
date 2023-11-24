package com.triforce.malacprodavac.domain.model.customers

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.domain.model.Product
import java.util.Date

data class FavoriteProduct(
    val id: Int,
    val customerId: Int,
    val productId: Int,
    val updatedAt: String,
    val createdAt: String,

    val product: Product?
)
