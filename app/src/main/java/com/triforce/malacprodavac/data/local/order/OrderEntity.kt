package com.triforce.malacprodavac.data.local.order

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.couriers.CourierEntity
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.domain.model.Courier

@Entity(foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId")
    ),
    ForeignKey(
        entity = CustomerEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("customerId")
    ),
    ForeignKey(
        entity = CourierEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("courierId")
    )
])
data class OrderEntity(
    @PrimaryKey
    val id: Int,
    val productId: Int,
    val quantity: Double,
    val paymentMethod: String, //convert to enum
    val orderStatus: String, //convert to enum
    val deliveryMethod: String, //convert to enum
    val customerId: Int,
    val courierId: Int?,
    val accepted: Boolean,
    val updatedAt: String,
    val createdAt: String
)

