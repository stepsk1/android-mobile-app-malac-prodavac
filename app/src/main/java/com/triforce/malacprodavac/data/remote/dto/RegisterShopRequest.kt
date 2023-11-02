package com.triforce.malacprodavac.data.remote.dto

import com.triforce.malacprodavac.domain.model.CreateUser

data class RegisterShopRequest(
    val createUser: CreateUser,
    val businessName: String
)
