package com.triforce.malacprodavac.domain.use_case.order

import com.triforce.malacprodavac.domain.repository.OrderRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.domain.model.Order
import kotlinx.coroutines.flow.Flow
import retrofit2.http.QueryMap

class GetAllOrders(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(
        fetchFromRemote: Boolean,
        @QueryMap() query: MutableMap<String, String>
    ): Flow<Resource<List<Order>>> {
        return repository.getOrders(fetchFromRemote, query)
    }
}