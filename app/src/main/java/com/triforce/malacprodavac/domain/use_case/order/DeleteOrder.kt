package com.triforce.malacprodavac.domain.use_case.order

import com.triforce.malacprodavac.domain.repository.OrderRepository

class DeleteOrder (
    private val repository: OrderRepository
){
    suspend operator fun invoke(id: Int){
        repository.deleteOrder(id)
    }
}