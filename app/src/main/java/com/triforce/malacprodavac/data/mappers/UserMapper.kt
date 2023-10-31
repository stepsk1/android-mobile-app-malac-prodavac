package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.user.UserEntity
import com.triforce.malacprodavac.data.remote.dto.UserDto
import com.triforce.malacprodavac.domain.model.User

fun UserEntity.toUser(): User
        = User(
    id = userId ?: 0,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    address = address,
    phoneNumber = phoneNumber,
    addressLatitude = addressLatitude,
    addressLongitude = addressLongitude,
    createdAt = createAt,
    updatedAt = updateAt,
    paymentMethod = paymentMethod,
    roles = roles,
    currency = currency,
    customer = customer,
    courier = courier,
    shop = shop
)

fun UserDto.toUser(): User
        = User(
    id = userId,
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    address = address,
    phoneNumber = phoneNumber,
    updatedAt = updateAt,
    createdAt = createAt,
    addressLatitude = addressLatitude,
    addressLongitude = addressLongitude,
    paymentMethod = paymentMethod,
    roles = roles,
    currency = currency,
    customer = customer,
    courier = courier,
    shop = shop
)

fun UserDto.toUserEntity(): UserEntity =
    UserEntity(
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        address = address,
        phoneNumber = phoneNumber,
        updateAt = updateAt,
        createAt = createAt,
        addressLatitude = addressLatitude,
        addressLongitude = addressLongitude,
        paymentMethod = paymentMethod,
        roles = roles,
        currency = currency,
        customer = customer,
        courier = courier,
        shop = shop
    )
