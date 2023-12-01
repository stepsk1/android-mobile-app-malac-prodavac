package com.triforce.malacprodavac.domain.use_case.product.replies.replies

import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.CreateReviewReplyDto
import com.triforce.malacprodavac.domain.repository.products.reviews.replies.ReviewRepliesRepository

data class CreateReviewReply(
    val repository: ReviewRepliesRepository
) {
    suspend operator fun invoke(
        productId: Int,
        reviewId: Int,
        createReviewReply: CreateReviewReplyDto
    ) =
        repository.createReviewReply(productId, reviewId, createReviewReply)
}
