package com.triforce.malacprodavac.data.mappers.users.userMedias

import com.triforce.malacprodavac.data.local.user.userMedias.UserMediaEntity
import com.triforce.malacprodavac.domain.model.UserMedia

fun UserMedia.toUserMediaEntity(): UserMediaEntity = UserMediaEntity(
    id = id,
    userId = userId,
    key = key,
    originalName = originalName,
    name = name,
    mimetype = mimetype,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun UserMediaEntity.toUserMedia(): UserMedia = UserMedia(
    id = id,
    userId = userId,
    key = key,
    originalName = originalName,
    name = name,
    mimetype = mimetype,
    createdAt = createdAt,
    updatedAt = updatedAt
)