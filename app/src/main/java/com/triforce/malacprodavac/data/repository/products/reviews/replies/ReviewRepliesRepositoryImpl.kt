package com.triforce.malacprodavac.data.repository.products.reviews.replies

import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.CreateReviewReplyDto
import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.ReviewReply
import com.triforce.malacprodavac.domain.repository.products.reviews.replies.ReviewRepliesRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ReviewRepliesRepositoryImpl @Inject constructor() : ReviewRepliesRepository {
    override suspend fun createReviewReply(
        productId: Int,
        reviewId: Int,
        createReviewReply: CreateReviewReplyDto
    ): Flow<Resource<ReviewReply>> {

        TODO("Not yet implemented")
    }

    override suspend fun getReviewReplies(
        productId: Int,
        reviewId: Int
    ): Flow<Resource<List<ReviewReply>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getReviewReply(
        productId: Int,
        reviewId: Int,
        replyId: Int
    ): Flow<Resource<ReviewReply>> {
        TODO("Not yet implemented")
    }
}