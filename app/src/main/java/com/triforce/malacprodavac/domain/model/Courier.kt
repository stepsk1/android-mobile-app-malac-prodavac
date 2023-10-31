package com.triforce.malacprodavac.domain.model

data class Courier(
    val id: Int,
    val createdAt: String,
    val currentLocation: String,
    val currentLocationLatitude: Int,
    val currentLocationLongitude: Int,
    val pricePerKilometer: Int,
    val upStringdAt: String,
    val userId: Int
)