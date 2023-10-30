package com.triforce.malacprodavac.domain.model

data class User(

    var id: Int,
    var email: String,
    var firstName: String,
    var lastName: String,
    var role: String,
    var profilePicUrl: String
)