package com.triforce.malacprodavac.data.mappers

import com.triforce.malacprodavac.data.local.customers.CustomerEntity
import com.triforce.malacprodavac.domain.model.Customer

fun CustomerEntity.toCustomer(): Customer = Customer(
    id = id,
    userId = userId,
    createdAt = createdAt,
    updatedAt = updatedAt,

    user = null
)

