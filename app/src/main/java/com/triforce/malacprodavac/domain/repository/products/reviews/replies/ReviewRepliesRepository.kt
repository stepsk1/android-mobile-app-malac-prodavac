package com.triforce.malacprodavac.domain.repository.products.reviews.replies

import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.CreateReviewReplyDto
import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.ReviewReply
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ReviewRepliesRepository {
    suspend fun createReviewReply(
        productId: Int,
        reviewId: Int,
        createReviewReply: CreateReviewReplyDto
    ): Flow<Resource<ReviewReply>>

    suspend fun getReviewReplies(
        productId: Int,
        reviewId: Int
    ): Flow<Resource<List<ReviewReply>>>

    suspend fun getReviewReply(
        productId: Int,
        reviewId: Int,
        replyId: Int
    ): Flow<Resource<ReviewReply>>
}