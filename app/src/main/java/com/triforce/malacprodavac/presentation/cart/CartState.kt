package com.triforce.malacprodavac.presentation.cart

import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.domain.model.shops.Shop
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class CartState (
    val totalPrice: Double = 0.00,

    val selectedShipping: DeliveryMethod = DeliveryMethod.SelfPickup,
    val selectedPayment: PaymentMethod = PaymentMethod.PayPal,
    val selectedAddress: String = "",

    val user: User? = null,
    val token: String? = null,
    val profileImageUrl: String? = null,
    val profileImageKey: String? = null,

    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,

    val shippingOptions: Map<DeliveryMethod, String> = mapOf(
        DeliveryMethod.SelfPickup to "Lično preuzimanje",
        DeliveryMethod.ByCourier to "Kurirska dostava"
    ),

    val paymentOptions: Map<PaymentMethod, String> = mapOf(
        PaymentMethod.PayPal to "PayPal",
        PaymentMethod.OnDelivery to "Lično/Pouzećem"
    ),

    val localDate: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
    val localTime: String = LocalTime.NOON.toString(),

    val scheduleDate: String = "",
    val scheduleTime: String = "",
)