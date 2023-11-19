package com.triforce.malacprodavac.domain.model

data class Courier(
    val id: Int,
    val userId: Int,
    val routeStartLatitude: Double?,
    val routeStartLongitude: Double?,
    val routeEndLatitude: Double?,
    val routeEndLongitude: Double?,
    val updatedAt: String,
    val createdAt: String,

    val user:User?
)