package com.triforce.malacprodavac.data.remote.orders.dto

import com.triforce.malacprodavac.domain.model.CreateOrder
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod


data class CreateOrderDto(
    override val deliveryMethod: DeliveryMethod,
    override val paymentMethod: PaymentMethod,
    override val productId: Int,
    override val quantity: Int
): CreateOrder
