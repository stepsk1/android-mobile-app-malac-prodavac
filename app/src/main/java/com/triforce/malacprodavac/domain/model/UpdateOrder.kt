package com.triforce.malacprodavac.domain.model

import com.triforce.malacprodavac.util.enum.OrderStatus

interface UpdateOrder {
    val orderStatus: String?
    val accepted: Boolean?
}