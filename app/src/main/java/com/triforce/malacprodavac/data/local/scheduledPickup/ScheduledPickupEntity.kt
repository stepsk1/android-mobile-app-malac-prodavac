package com.triforce.malacprodavac.data.local.scheduledPickup

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.order.OrderEntity

@Entity(
    foreignKeys = [ForeignKey(
        entity = OrderEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("orderId")
    )
    ]
)
data class ScheduledPickupEntity(
    @PrimaryKey
    val id: Int,
    val orderId: Int,
    val timeOfDay: String,
    val date: String,
    val accepted: Boolean,
    val updatedAt: String,
    val createdAt: String
)
