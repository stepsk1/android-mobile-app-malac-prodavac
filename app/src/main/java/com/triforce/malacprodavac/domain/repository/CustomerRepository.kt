package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.data.remote.customers.dto.CreateFavoriteProductDto
import com.triforce.malacprodavac.domain.model.CreateCustomer
import com.triforce.malacprodavac.domain.model.Customer
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun registerCustomer(
        createCustomer: CreateCustomer
    ): Flow<Resource<Customer>>

    suspend fun getFavoriteProducts(
        customerId: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<FavoriteProduct>>>
    suspend fun insertFavoriteProduct(customerId: Int, createFavoriteProductDto: CreateFavoriteProductDto): Flow<Resource<FavoriteProduct>>
    suspend fun deleteFavoriteProduct(id: Int, favoriteProductId: Int): Flow<Resource<FavoriteProduct>>
}
