package com.triforce.malacprodavac.domain.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class User(
    @PrimaryKey
    val id: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("addressLatitude")
    val addressLatitude: Int,
    @SerializedName("addressLongitude")
    val addressLongitude: Int,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("roles")
    val roles: List<String>?,
    @SerializedName("customer")
    val customer: Customer?,
    @SerializedName("shop")
    val shop: Shop?,
    @SerializedName("courier")
    val courier: Courier?,
    @SerializedName("upStringdAt")
    val upStringdAt: String?
)