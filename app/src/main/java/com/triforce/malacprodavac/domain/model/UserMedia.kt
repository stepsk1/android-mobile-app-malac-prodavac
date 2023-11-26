package com.triforce.malacprodavac.domain.model

data class UserMedia(
    val id: Int,
    val userId: Int,
    val mimetype: String,
    val key: String,
    val originalName: String,
    val name: String?,
    val updatedAt: String,
    val createdAt: String
)
