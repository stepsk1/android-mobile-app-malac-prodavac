package com.triforce.malacprodavac.data.remote.dto

import com.triforce.malacprodavac.domain.model.CreateUser

data class RegisterCourierRequest(
    val createUser: CreateUser,
    val pricePerKilometer: Double
)
