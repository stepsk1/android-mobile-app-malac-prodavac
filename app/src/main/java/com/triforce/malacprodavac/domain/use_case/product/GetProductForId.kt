package com.triforce.malacprodavac.domain.use_case.product

import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.util.Resource
import kotlinx.coroutines.flow.Flow

class GetProductForId(

    private val repository: ProductRepository

) {

    suspend operator fun invoke(id: Int, fetchFromRemote: Boolean): Flow<Resource<Product>> {

        return repository.getProductForId(id, fetchFromRemote)

    }
}