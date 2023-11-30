package com.triforce.malacprodavac.data.mappers.products.reviews

import com.triforce.malacprodavac.data.local.product.reviews.ReviewEntity
import com.triforce.malacprodavac.data.mappers.products.reviews.replies.toReviewReply
import com.triforce.malacprodavac.domain.model.products.reviews.Review

fun Review.toReviewEntity(): ReviewEntity =
    ReviewEntity(
        id = id,
        customerId = customerId,
        text = text,
        updatedAt = updatedAt,
        createdAt = createdAt,
        productId = productId,
        rating = rating
    )

fun ReviewEntity.toReview(): Review =
    Review(
        id = id,
        customerId = customerId,
        text = text,
        updatedAt = updatedAt,
        createdAt = createdAt,
        productId = productId,
        rating = rating,
        reviewReplies = reviewReplies.map { it.toReviewReply() }
    )
