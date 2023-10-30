package com.triforce.malacprodavac.data.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    val userId: Int,
    val email: String,
    @field: Json(name = "profilePicId") val profilePicUrl: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val role: String
)