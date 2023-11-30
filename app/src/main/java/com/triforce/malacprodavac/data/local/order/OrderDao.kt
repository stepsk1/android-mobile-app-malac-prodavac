package com.triforce.malacprodavac.data.local.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDao {

    @Query("SELECT * FROM OrderEntity")
    suspend fun  getAllOrders(): List<OrderEntity>

    @Query("""SELECT * FROM OrderEntity
        WHERE id = :id""")
    suspend fun getOrderForId(id: Int): List<OrderEntity>

    @Query("DELETE FROM OrderEntity")
    suspend fun clearOrders()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(categories: List<OrderEntity>)
}