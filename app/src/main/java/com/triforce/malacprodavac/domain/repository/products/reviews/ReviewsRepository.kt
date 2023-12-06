package com.triforce.malacprodavac.domain.repository.products.reviews

import com.triforce.malacprodavac.domain.model.products.reviews.CreateReviewDto
import com.triforce.malacprodavac.domain.model.products.reviews.Review
import com.triforce.malacprodavac.domain.model.products.reviews.UpdateReviewDto
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ReviewsRepository {
    suspend fun createReview(productId: Int, createReview: CreateReviewDto): Flow<Resource<Review>>

    suspend fun getReviews(productId: Int): Flow<Resource<List<Review>>>

    suspend fun getReview(productId: Int, reviewId: Int): Flow<Resource<Review>>

    suspend fun updateReview(
        productId: Int,
        reviewId: Int,
        updateReview: UpdateReviewDto
    ): Flow<Resource<Review>>
}