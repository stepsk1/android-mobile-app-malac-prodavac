package com.triforce.malacprodavac.data.local.shops

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopDao {
    @Query("SELECT * FROM ShopEntity")
    suspend fun getShops(): List<ShopEntity>

    @Query("SELECT * FROM ShopEntity WHERE id = :id")
    suspend fun getShop(id: Int): List<ShopEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShops(shops: List<ShopEntity>)
}