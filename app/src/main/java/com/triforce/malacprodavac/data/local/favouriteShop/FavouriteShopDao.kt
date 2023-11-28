package com.triforce.malacprodavac.data.local.favouriteShop

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavouriteShopDao {

    @Query("SELECT * FROM FavouriteShopEntity")
    suspend fun getFavouriteShops(): List<FavouriteShopEntity>

    @Delete
    suspend fun deleteFavouriteShop(shop: FavouriteShopEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteShop(shop: FavouriteShopEntity)
}