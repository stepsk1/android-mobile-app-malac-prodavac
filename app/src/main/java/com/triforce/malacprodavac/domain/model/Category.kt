package com.triforce.malacprodavac.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Category(
    val id: Int,
    val parentCategoryId: Int?,
    val name: String,
    val updatedAt: String,
    val createdAt: String,

    val parentCategory: Category?,
    val subCategories: List<Category>?
)
