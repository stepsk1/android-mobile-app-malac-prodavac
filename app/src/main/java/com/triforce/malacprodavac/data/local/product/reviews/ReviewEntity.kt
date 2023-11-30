package com.triforce.malacprodavac.data.local.product.reviews

import androidx.room.Entity
import androidx.room.Ignore
import com.triforce.malacprodavac.data.local.product.reviews.replies.ReviewReplyEntity

@Entity
data class ReviewEntity(
    val id: Int,
    val customerId: Int,
    val productId: Int,
    val text: String,
    val rating: Int,
    val updatedAt: String,
    val createdAt: String,
) {
    @Ignore
    val reviewReplies: List<ReviewReplyEntity> = listOf()
}
