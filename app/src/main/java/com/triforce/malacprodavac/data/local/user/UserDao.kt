package com.triforce.malacprodavac.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    suspend fun getUsers(): List<UserEntity>

    @Query("""SELECT * FROM userentity
                WHERE userId = :id""")
    suspend fun getUserForId(id: Int): List<UserEntity>

    @Query("""SELECT * FROM userentity
        WHERE email = :email""")
    suspend fun getUserForEmail(email: String): List<UserEntity>

    @Query("DELETE FROM userentity")
    suspend fun clearUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<UserEntity>)
}