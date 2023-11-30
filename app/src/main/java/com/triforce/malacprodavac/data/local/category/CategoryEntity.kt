package com.triforce.malacprodavac.data.local.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parentCategoryId")
    )]
)
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val parentCategoryId: Int?,
    val updatedAt: String,
    val createdAt: String,
) {
    @Ignore
    val parentCategory: CategoryEntity? = null

    @Ignore
    val subCategories: List<CategoryEntity> = listOf()
}
