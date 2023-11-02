package com.triforce.malacprodavac.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreateUserDto(
    @SerializedName("currency")
    val currency: String? = "RSD",
    @SerializedName("paymentMethod")
    val paymentMethod: String? = "onDelivery",
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("addressLatitude")
    val addressLatitude: Int? = 0,
    @SerializedName("addressLongitude")
    val addressLongitude: Int? = 0,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = ""
)
