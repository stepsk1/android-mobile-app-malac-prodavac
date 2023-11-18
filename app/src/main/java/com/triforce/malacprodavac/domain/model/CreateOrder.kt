package com.triforce.malacprodavac.domain.model

import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

interface CreateOrder {
    val deliveryMethod: DeliveryMethod
    val paymentMethod: PaymentMethod
    val productId: Int
    val quantity: Int
}
