package com.triforce.malacprodavac.data.local.product.productMedia

import java.util.Date

data class ProductMediaEntity(
    val id: Int,
    val mimetype: String,
    val key: String,
    val productId: Int,
    val originalName: String,
    val name: String,
    val updatedAt: Date,
    val createdAt: Date
)
