package com.triforce.malacprodavac.domain.use_case.order

import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.QueryMap

class GetOrderForId(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        orderId: Int,
        fetchFromRemote: Boolean,
        @QueryMap() query: MutableMap<String, String>
    ) : Flow<Resource<Order>> {
        return repository.getOrder(orderId, fetchFromRemote)
    }
}