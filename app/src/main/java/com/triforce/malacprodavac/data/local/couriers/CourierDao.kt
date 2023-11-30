package com.triforce.malacprodavac.data.local.couriers

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourierDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourier(couriers: List<CourierEntity>)

    @Query("""SELECT * FROM CourierEntity""")
    suspend fun getCouriers(): List<CourierEntity>

    @Query("""SELECT * FROM CourierEntity WHERE id = :id""")
    suspend fun getCourier(id: Int): CourierEntity

    @Delete
    suspend fun deleteCourier(courier: CourierEntity)
}