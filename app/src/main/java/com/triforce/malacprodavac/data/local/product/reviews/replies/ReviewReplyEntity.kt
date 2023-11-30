package com.triforce.malacprodavac.data.local.product.reviews.replies

data class ReviewReplyEntity(
    val id: Int,
    val text: String,
    val reviewId: Int,
    val updatedAt: String,
    val createdAt: String
)