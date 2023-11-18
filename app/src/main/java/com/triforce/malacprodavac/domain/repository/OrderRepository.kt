package com.triforce.malacprodavac.domain.repository


import com.triforce.malacprodavac.domain.model.CreateOrder
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.domain.model.UpdateOrder
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun getOrders(
        fetchFromRemote: Boolean,
        queryMap: MutableMap<String, String>
    ): Flow<Resource<List<Order>>>


//    suspend fun getOrdersByCustomer(
//        customerId: Int,
//        fetchFromRemote: Boolean,
//        queryMap: MutableMap<String, String>
//    ): Flow<Resource<List<Order>>>
//
//    suspend fun getOrdersByCourier(
//        courierId: Int,
//        fetchFromRemote: Boolean,
//        queryMap: MutableMap<String, String>
//    ): Flow<Resource<List<Order>>>
    suspend fun getOrder(
        id: Int,
        fetchFromRemote: Boolean,
    ): Flow<Resource<Order>>

    suspend fun deleteOrder(id: Int): Flow<Resource<Order>>
    suspend fun insertOrder(createOrder: CreateOrder): Flow<Resource<Order>>

    suspend fun updateOrder(id: Int, updateOrder: UpdateOrder): Flow<Resource<Order>>
}