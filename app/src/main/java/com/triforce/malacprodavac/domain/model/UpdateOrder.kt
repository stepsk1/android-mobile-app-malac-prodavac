package com.triforce.malacprodavac.domain.model

import com.triforce.malacprodavac.util.enum.OrderStatus

interface UpdateOrder {
    val orderStatus: OrderStatus?
    val accepted: Boolean?
}