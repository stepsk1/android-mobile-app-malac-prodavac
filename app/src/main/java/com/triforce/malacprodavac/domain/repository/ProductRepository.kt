package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(fetchFromRemote: Boolean): Flow<Resource<List<Product>>>
    suspend fun getProductForId(id: Int, fetchFromRemote: Boolean): Flow<Resource<Product>>
    suspend fun deleteProduct(product: Product)
    suspend fun insertProduct(product: Product)

}