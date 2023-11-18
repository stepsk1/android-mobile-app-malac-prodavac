package com.triforce.malacprodavac.data.remote.orders.dto

import com.triforce.malacprodavac.domain.model.UpdateOrder
import com.triforce.malacprodavac.util.enum.OrderStatus

data class UpdateOrderDto (
    override val orderStatus: OrderStatus?,
    override val accepted: Boolean?
): UpdateOrder