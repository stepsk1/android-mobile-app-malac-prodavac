package com.triforce.malacprodavac.data.local.favouriteShop

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.shops.ShopEntity

@Entity(
    foreignKeys = [ForeignKey(
        entity = ShopEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("shopId")
    ),
    ForeignKey(
        entity = CustomerEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("customerId")
    )]
)
data class FavouriteShopEntity (
    @PrimaryKey
    val id: Int,
    val customerId: Int,
    val shopId: Int,
    val updatedAt: String,
    val createdAt: String,

)