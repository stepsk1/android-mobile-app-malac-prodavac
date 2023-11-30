package com.triforce.malacprodavac.domain.use_case.order

import com.triforce.malacprodavac.domain.model.CreateOrder
import com.triforce.malacprodavac.domain.repository.OrderRepository
import java.util.InvalidPropertiesFormatException

class AddOrder (
    private val repository: OrderRepository
){

    suspend operator fun invoke(createOrder: CreateOrder){

        repository.insertOrder(createOrder)
    }
}