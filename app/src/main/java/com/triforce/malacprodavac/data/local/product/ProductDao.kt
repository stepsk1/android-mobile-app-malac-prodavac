package com.triforce.malacprodavac.data.local.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun getProducts(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE id = :id")
    suspend fun getProductForId(id: Int):ProductEntity

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)
}