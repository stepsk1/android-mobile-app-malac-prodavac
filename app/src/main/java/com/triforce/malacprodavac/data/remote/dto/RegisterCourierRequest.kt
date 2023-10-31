package com.triforce.malacprodavac.data.remote.dto

import com.triforce.malacprodavac.domain.model.User

data class RegisterCourierRequest(
    val user: User,
    val pricePerKilometer: Int
)
