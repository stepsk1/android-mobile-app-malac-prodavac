package com.triforce.malacprodavac.domain.model

import androidx.room.PrimaryKey
import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.Product

data class Order(
    val id: Int,
    val productId: Int,
    val quantity: Int,
    val paymentMethod: String, //convert to enum
    val orderStatus: String, //convert to enum
    val deliveryMethod: String, //convert to enum
    val customerId: Int,
    val courierId: Int?,
    val accepted: Boolean,
    val updatedAt: String,
    val createdAt: String,

    val product: Product?,
    val courier: Courier?
)
