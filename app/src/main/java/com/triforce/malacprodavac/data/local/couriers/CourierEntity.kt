package com.triforce.malacprodavac.data.local.couriers

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.user.UserEntity

@Entity(
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId")
    )]
)
data class CourierEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val routeStartLatitude: Double?,
    val routeStartLongitude: Double?,
    val routeEndLatitude: Double?,
    val routeEndLongitude: Double?,
    val updatedAt: String,
    val createdAt: String,
) {
    @Ignore
    val user: UserEntity? = null
}
