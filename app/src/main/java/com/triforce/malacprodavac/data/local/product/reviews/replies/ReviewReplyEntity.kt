package com.triforce.malacprodavac.data.local.product.reviews.replies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReviewReplyEntity(
    @PrimaryKey
    val id: Int,
    val text: String,
    val reviewId: Int,
    val updatedAt: String,
    val createdAt: String
)