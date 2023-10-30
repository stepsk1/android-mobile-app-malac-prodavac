package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.data.remote.dto.UserDto
import com.triforce.malacprodavac.domain.model.User

fun UserEntity.toUser(): User
        = User(
    id = userId ?: 0,
    email = email,
    firstName = firstName,
    lastName = lastName,
    profilePicUrl = profilePicUrl,
    role = role
)

fun UserDto.toUser(): User
        = User(
    id = userId,
    email = email,
    profilePicUrl = profilePicUrl,
    firstName = firstName,
    lastName = lastName,
    role = role
)

fun UserDto.toUserEntity(): UserEntity =
    UserEntity(
        email = email,
        profilePicUrl = profilePicUrl,
        firstName = firstName,
        lastName = lastName,
        role = role
    )
