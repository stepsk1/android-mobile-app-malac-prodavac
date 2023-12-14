package com.triforce.malacprodavac.data.repository.cart

import com.triforce.malacprodavac.presentation.cart.components.CartItem
import com.triforce.malacprodavac.util.enum.DeliveryMethod
import com.triforce.malacprodavac.util.enum.PaymentMethod

object CartRepository {
    private val cartItems: MutableList<CartItem> = mutableListOf()

    private var scheduleDate: String = ""
    private var scheduleTime: String = ""

    private var selectedShipping: DeliveryMethod = DeliveryMethod.SelfPickup
    private var selectedPayment: PaymentMethod = PaymentMethod.PayPal

    fun setShipping( selectedShipping: DeliveryMethod){
        this.selectedShipping = selectedShipping
    }

    fun setPayment( selectedPayment: PaymentMethod){
        this.selectedPayment = selectedPayment
    }

    fun getShipping(): DeliveryMethod{
        return selectedShipping;
    }

    fun getPayment(): PaymentMethod{
        return selectedPayment;
    }

    fun setScheduleDate(scheduleDate: String){
        this.scheduleDate = scheduleDate
    }

    fun setScheduleTime(scheduleTime :String){
        this.scheduleTime = scheduleTime
    }

    fun getScheduleDate(): String{
        return scheduleDate
    }

    fun getScheduleTime(): String{
        return scheduleTime
    }

    fun getCartItems(): List<CartItem> {
        return cartItems
    }

    fun addItemToCart(item: CartItem) {
        cartItems.add(item)
    }

    fun removeItemFromCart(item: CartItem) {
        cartItems.remove(item)

        if (cartItems.size == 0) clearCart()
    }

    fun clearCart() {
        cartItems.clear()
        scheduleDate = ""
        scheduleTime = ""
        selectedShipping = DeliveryMethod.SelfPickup
        selectedPayment = PaymentMethod.PayPal
    }

}