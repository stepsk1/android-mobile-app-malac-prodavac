package com.triforce.malacprodavac.data.remote.dto

import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.Shop

data class UserDto(
    val userId: Int,
    //@field: Json(name = "profilePicId") val profilePicUrl: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val address: String,
    val phoneNumber: String,
    val upStringdAt: String,
    val createdAt: String,
    val addressLatitude: Int,
    val addressLongitude: Int,
    val paymentMethod: String,
    val roles: List<String>,
    val currency: String,
    val customer: Customer?,
    val courier: Courier?,
    val shop: Shop?
)