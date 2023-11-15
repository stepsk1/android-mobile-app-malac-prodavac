package com.triforce.malacprodavac.data.local.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.domain.model.UserMedia

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String,
    val phoneNumber: String?,
    val addressLatitude: Double?,
    val addressLongitude: Double?,
    val paymentMethod: String,//Convert to enum
    val roles: List<String>,  //Convert to enum
    val currency: String, //Convert to enum
    val createdAt: String,
    val updatedAt: String,
)