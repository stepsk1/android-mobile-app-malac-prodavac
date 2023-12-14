package com.triforce.malacprodavac.domain.use_case.favoriteProduct

import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetFavProducts(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<List<FavoriteProduct>>> {
        val query = FilterBuilder.buildFilterQueryMap(Filter(filter = listOf(), order = null, limit = null, offset = null))
        return repository.getFavoriteProducts(id,true, query)
    }
}