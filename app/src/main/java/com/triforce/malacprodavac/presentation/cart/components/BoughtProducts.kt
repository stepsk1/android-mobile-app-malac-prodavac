package com.triforce.malacprodavac.presentation.cart.components

import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object BoughtProducts {

    var formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy")
    val listOfBoughtProducts: MutableList<ProductAmount> = mutableListOf()
    var paymentMethod: PaymentMethod = PaymentMethod.OnDelivery
    var address: String = ""
    var deliveryMethod: DeliveryMethod = DeliveryMethod.ByCourier
    var localDate: String = LocalDate.now().format(formatter)
    var localTime: String = LocalTime.NOON.toString()
}

