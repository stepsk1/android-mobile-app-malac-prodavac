package com.triforce.malacprodavac.domain.model.customers

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.domain.model.Shop
import java.util.Date

data class FavoriteShop(
    val id: Int,
    val customerId: Int,
    val shopId: Int,
    val updatedAt: Date,
    val createdAt: Date,
    val shop: Shop?
)
