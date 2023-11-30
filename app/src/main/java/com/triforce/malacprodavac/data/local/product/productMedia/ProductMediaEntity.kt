package com.triforce.malacprodavac.data.local.product.productMedia

import androidx.room.Entity

@Entity
data class ProductMediaEntity(
    val id: Int,
    val mimetype: String,
    val key: String,
    val productId: Int,
    val originalName: String,
    val name: String?,
    val updatedAt: String,
    val createdAt: String
)
