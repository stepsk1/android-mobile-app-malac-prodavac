package com.triforce.malacprodavac.domain.use_case.product.replies.replies

import com.triforce.malacprodavac.domain.repository.products.reviews.replies.ReviewRepliesRepository

data class GetReviewReplies(
    val repository: ReviewRepliesRepository
) {
    suspend operator fun invoke(productId: Int, reviewId: Int) =
        repository.getReviewReplies(productId, reviewId)
}
