package com.triforce.malacprodavac.domain.repository

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.QueryMap

interface ProductRepository {
    suspend fun getProducts(
        categoryId: Int,
        fetchFromRemote: Boolean,
        queryMap: MutableMap<String, String>
    ): Flow<Resource<List<Product>>>
    suspend fun  getProduct(
        id: Int,
        fetchFromRemote: Boolean,
    ):Flow<Resource<Product>>
    suspend fun deleteProduct(product: Product)
    suspend fun insertProduct(product: Product)

}