package com.triforce.malacprodavac.data.local.favoriteProduct

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.product.ProductEntity
import com.triforce.malacprodavac.domain.model.Product
@Entity(
    foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId")
    ),
    ForeignKey(
        entity = CustomerEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("customerId")
    )]
)
data class FavouriteProductEntity(
    @PrimaryKey
    val id: Int,
    val customerId: Int,
    val productId: Int,
    val updatedAt: String,
    val createdAt: String

    //val product: ProductEntity?
)
