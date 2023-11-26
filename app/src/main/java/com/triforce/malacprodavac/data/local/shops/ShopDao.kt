package com.triforce.malacprodavac.data.local.shops

import androidx.room.Dao
import androidx.room.Query
import com.triforce.malacprodavac.data.local.product.ProductEntity

@Dao
interface ShopDao {
    @Query("SELECT * FROM ShopEntity")
    suspend fun getShops(): List<ShopEntity>

    @Query("SELECT * FROM ShopEntity WHERE id = :id")
    suspend fun getShop(id: Int): List<ShopEntity>
}