package com.triforce.malacprodavac.domain.use_case.product.replies

data class ReviewUseCase(
    val createReview: CreateReview,
    val getReviews: GetReviews,
    val getReview: GetReview,
    val updateReview: UpdateReview
)
