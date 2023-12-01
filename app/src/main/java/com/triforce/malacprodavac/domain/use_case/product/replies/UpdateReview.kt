package com.triforce.malacprodavac.domain.use_case.product.replies

import com.triforce.malacprodavac.domain.model.products.reviews.UpdateReviewDto
import com.triforce.malacprodavac.domain.repository.products.reviews.ReviewsRepository

data class UpdateReview(val repository: ReviewsRepository) {
    suspend operator fun invoke(productId: Int, reviewId: Int, updateReview: UpdateReviewDto) =
        repository.updateReview(productId, reviewId, updateReview)
}