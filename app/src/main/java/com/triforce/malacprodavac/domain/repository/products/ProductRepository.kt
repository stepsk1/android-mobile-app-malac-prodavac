package com.triforce.malacprodavac.domain.repository.products

import com.triforce.malacprodavac.domain.model.CreateProduct
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.UpdateProduct
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(
        categoryId: Int,
        fetchFromRemote: Boolean,
        queryMap: MutableMap<String, String>
    ): Flow<Resource<List<Product>>>

    suspend fun getProduct(
        id: Int,
        fetchFromRemote: Boolean,
    ): Flow<Resource<Product>>

    suspend fun deleteProduct(id: Int): Flow<Resource<Product>>
    suspend fun insertProduct(createProduct: CreateProduct): Flow<Resource<Product>>

    suspend fun updateProduct(id: Int, updateProduct: UpdateProduct): Flow<Resource<Product>>
}