package com.triforce.malacprodavac.presentation.orders

sealed class OrderEvent {
    object changeStatusOfOrder: OrderEvent()
}