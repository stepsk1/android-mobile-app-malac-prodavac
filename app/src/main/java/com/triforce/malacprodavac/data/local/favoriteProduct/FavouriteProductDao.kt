package com.triforce.malacprodavac.data.local.favoriteProduct

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouriteProductDao {
    @Query("SELECT * FROM FavouriteProductEntity")
    suspend fun getFavoriteProducts(): List<FavouriteProductEntity>

    @Delete
    suspend fun deleteFavouriteProduct(product: FavouriteProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteProduct(product: FavouriteProductEntity)
}