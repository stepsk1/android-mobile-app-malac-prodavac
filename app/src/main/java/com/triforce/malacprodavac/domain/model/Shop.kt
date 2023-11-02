package com.triforce.malacprodavac.domain.model

import androidx.room.PrimaryKey

data class Shop(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val businessName: String,
    val createdAt: String,
    val updatedAt: String
)