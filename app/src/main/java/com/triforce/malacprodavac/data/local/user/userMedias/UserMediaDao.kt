package com.triforce.malacprodavac.data.local.user.userMedias

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserMediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserMedia(userMedias: List<UserMediaEntity>)

    @Query("""SELECT * FROM UserMediaEntity WHERE userId = :userId""")
    suspend fun getUserMedias(userId: Int): List<UserMediaEntity>

    @Query("""SELECT * FROM UserMediaEntity WHERE id = :id""")
    suspend fun getUserMedia(id: Int): UserMediaEntity

    @Delete
    suspend fun delete(userMediaEntity: UserMediaEntity)

}