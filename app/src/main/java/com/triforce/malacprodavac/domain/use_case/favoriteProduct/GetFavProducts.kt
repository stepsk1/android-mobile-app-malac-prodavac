package com.triforce.malacprodavac.domain.use_case.favoriteProduct

import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetFavProducts(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<List<FavoriteProduct>>> {
        return repository.getFavoriteProducts(id,true)
    }
}