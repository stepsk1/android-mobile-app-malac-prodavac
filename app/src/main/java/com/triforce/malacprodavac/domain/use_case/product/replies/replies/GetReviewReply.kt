package com.triforce.malacprodavac.domain.use_case.product.replies.replies

import com.triforce.malacprodavac.domain.repository.products.reviews.replies.ReviewRepliesRepository

data class GetReviewReply(val repository: ReviewRepliesRepository) {
    suspend operator fun invoke(productId: Int, reviewId: Int, replyId: Int) =
        repository.getReviewReply(productId, reviewId, replyId)
}
