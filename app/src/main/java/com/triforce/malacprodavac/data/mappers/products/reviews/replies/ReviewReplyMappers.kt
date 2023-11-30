package com.triforce.malacprodavac.data.mappers.products.reviews.replies

import com.triforce.malacprodavac.data.local.product.reviews.replies.ReviewReplyEntity
import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.ReviewReply

fun ReviewReply.toReviewReplyEntity(): ReviewReplyEntity = ReviewReplyEntity(
    id = id,
    text = text,
    reviewId = reviewId,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun ReviewReplyEntity.toReviewReply(): ReviewReply = ReviewReply(
    id = id,
    text = text,
    reviewId = reviewId,
    createdAt = createdAt,
    updatedAt = updatedAt
)