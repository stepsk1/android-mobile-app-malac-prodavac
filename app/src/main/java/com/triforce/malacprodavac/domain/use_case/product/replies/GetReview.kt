package com.triforce.malacprodavac.domain.use_case.product.replies

import com.triforce.malacprodavac.domain.repository.products.reviews.ReviewsRepository

data class GetReview(val repository: ReviewsRepository) {
    suspend operator fun invoke(productId: Int, reviewId: Int) =
        repository.getReview(productId, reviewId)
}
