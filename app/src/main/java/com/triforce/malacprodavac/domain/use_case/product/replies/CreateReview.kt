package com.triforce.malacprodavac.domain.use_case.product.replies

import com.triforce.malacprodavac.domain.model.products.reviews.CreateReviewDto
import com.triforce.malacprodavac.domain.repository.products.reviews.ReviewsRepository

data class CreateReview(val repository: ReviewsRepository) {
    suspend operator fun invoke(productId: Int, createReview: CreateReviewDto) =
        repository.createReview(productId, createReview)
}
