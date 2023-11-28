package com.triforce.malacprodavac.data.local.product

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.category.CategoryEntity
import com.triforce.malacprodavac.data.local.product.productMedia.ProductMediaEntity
import com.triforce.malacprodavac.data.local.shops.ShopEntity

@Entity(
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("categoryId")
    )]
)
data class ProductEntity(
    @PrimaryKey
    val id: Int = 0,
    val available: Boolean = false,
    val price: Double,
    val unitOfMeasurement: String, //Convert to enum
    val rating: Double?,
    val availableAtLatitude: Double?,
    val availableAtLongitude: Double?,
    val availableFromHours: Double?,
    val availableTillHours: Double?,
    val currency: String, //Convert to enum
    val shopId: Int,
    val title: String,
    val desc: String,
    val ratingsCount: Double?,
    val availableAt: String?,
    val categoryId: Int,
    val createdAt: String,
    val updatedAt: String,
) {


    @Ignore
    val shop: ShopEntity? = null

    @Ignore
    val category: CategoryEntity? = null

    @Ignore
    val productImages: List<ProductMediaEntity> = listOf()
}