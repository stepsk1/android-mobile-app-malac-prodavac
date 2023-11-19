package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.couriers.CourierEntity
import com.triforce.malacprodavac.domain.model.Courier

fun CourierEntity.toCourier(): Courier = Courier(
    id = id,
    userId = userId,
    routeStartLatitude = routeStartLatitude,
    routeStartLongitude = routeStartLongitude,
    routeEndLatitude = routeEndLatitude,
    routeEndLongitude = routeEndLongitude,
    createdAt = createdAt,
    updatedAt = updatedAt,

    user = null
)

