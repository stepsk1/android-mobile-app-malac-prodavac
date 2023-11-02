package com.triforce.malacprodavac.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.triforce.malacprodavac.domain.model.CreateUser
import com.triforce.malacprodavac.domain.model.User

data class RegisterCustomerRequest(
    @SerializedName("createUser")
    val createUser: CreateUser
)
