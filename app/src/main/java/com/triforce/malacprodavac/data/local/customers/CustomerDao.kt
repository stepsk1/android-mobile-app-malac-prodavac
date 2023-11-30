package com.triforce.malacprodavac.data.local.customers

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomers(customers: List<CustomerEntity>)

    @Query("""SELECT * FROM CustomerEntity""")
    suspend fun getCustomers(): List<CustomerEntity>

    @Query("""SELECT * FROM CustomerEntity WHERE id= :id""")
    suspend fun getCustomer(id: Int): CustomerEntity


    @Delete
    suspend fun deleteCustomer(customer: CustomerEntity)

}