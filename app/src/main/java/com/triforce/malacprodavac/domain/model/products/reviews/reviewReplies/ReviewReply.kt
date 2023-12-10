package com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies

data class ReviewReply(
    val id: Int,
    val text: String,
    val reviewId: Int,
    val updatedAt: String,
    val createdAt: String
)