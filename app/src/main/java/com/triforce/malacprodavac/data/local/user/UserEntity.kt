package com.triforce.malacprodavac.data.local.user

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.triforce.malacprodavac.data.local.couriers.CourierEntity
import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.data.local.shops.ShopEntity
import com.triforce.malacprodavac.data.local.user.userMedias.UserMediaEntity

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String?,
    val phoneNumber: String?,
    val addressLatitude: Double?,
    val addressLongitude: Double?,
    val paymentMethod: String,//Convert to enum
    val roles: List<String>,  //Convert to enum
    val currency: String, //Convert to enum
    val createdAt: String,
    val updatedAt: String,
) {
    @Ignore
    val profilePicture: UserMediaEntity? = null

    @Ignore
    val customer: CustomerEntity? = null

    @Ignore
    val courier: CourierEntity? = null

    @Ignore
    val shop: ShopEntity? = null
}
