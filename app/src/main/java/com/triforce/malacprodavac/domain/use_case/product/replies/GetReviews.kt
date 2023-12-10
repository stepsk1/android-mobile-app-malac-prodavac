package com.triforce.malacprodavac.domain.use_case.product.replies

import com.triforce.malacprodavac.domain.repository.products.reviews.ReviewsRepository

data class GetReviews(val repository: ReviewsRepository) {
    suspend operator fun invoke(productId: Int) =
        repository.getReviews(productId)
}
