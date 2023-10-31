package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.Shop
import java.time.LocalDateTime

@Entity
data class UserEntity(
    @PrimaryKey val userId: Int? = null,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val address: String,
    val phoneNumber: String,
    val updateAt: LocalDateTime,
    val createAt: LocalDateTime,
    val addressLatitude: Int,
    val addressLongitude: Int,
    val paymentMethod: String,
    val roles: List<String>,
    val currency: String,
    val customer: Customer?,
    val courier: Courier?,
    val shop: Shop?
)