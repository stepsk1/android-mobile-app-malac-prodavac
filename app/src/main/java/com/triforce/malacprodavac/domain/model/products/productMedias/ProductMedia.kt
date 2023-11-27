package com.triforce.malacprodavac.domain.model.products.productMedias


data class ProductMedia(
    val id: Int,
    val productId: Int,
    val mimetype: String,
    val key: String,
    val originalName: String,
    val name: String?,
    val updatedAt: String,
    val createdAt: String
)
