package com.triforce.malacprodavac.domain.model.products.reviews

import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.products.reviews.reviewReplies.ReviewReply

data class Review(
    val id: Int,
    val customerId: Int,
    val productId: Int,
    val text: String,
    val rating: Int,
    val updatedAt: String,
    val createdAt: String,


    val customer: Customer?,
    val reviewReplies: List<ReviewReply>?

)
