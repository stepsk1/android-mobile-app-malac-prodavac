package com.triforce.malacprodavac.data.remote.dto

data class RegisterRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    //@field: Json(name="profilePicId") val profilePic: String,
    val password: String,
    val repeatPassword: String,
    val role: String
)
