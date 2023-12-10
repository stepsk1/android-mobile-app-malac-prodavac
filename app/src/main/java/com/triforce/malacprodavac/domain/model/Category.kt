package com.triforce.malacprodavac.domain.model

data class Category(
    val id: Int,
    val parentCategoryId: Int?,
    val name: String,
    val updatedAt: String,
    val createdAt: String,

    val parentCategory: Category?,
    val subCategories: List<Category>?
) {
    override fun toString(): String {
        return name
    }
}
