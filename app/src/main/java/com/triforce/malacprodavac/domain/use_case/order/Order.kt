package com.triforce.malacprodavac.domain.use_case.order

data class Order(
    val getAllOrders: GetAllOrders,
    val getOrderForId: GetOrderForId,
    val addOrder: AddOrder,
    val deleteOrder: DeleteOrder
)
