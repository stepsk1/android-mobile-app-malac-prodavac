package com.triforce.malacprodavac.presentation.cart

import com.triforce.malacprodavac.presentation.cart.components.ProductAmount
import com.triforce.malacprodavac.presentation.cart.components.TotalPrice
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

object BuyedProducts {

    val listOfBuyedProducts: MutableList<ProductAmount> = mutableListOf()
    var paymentMethod: PaymentMethod = PaymentMethod.OnDelivery
    var address: String = ""
    var deliveryMethod: DeliveryMethod = DeliveryMethod.ByCourier
}

