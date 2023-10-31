package com.triforce.malacprodavac.data.remote.dto

import com.triforce.malacprodavac.domain.model.User

data class RegisterShopRequest(
    val user: User,
    val businessName: String
)
