package com.triforce.malacprodavac.domain.use_case.favoriteShop

import com.triforce.malacprodavac.domain.model.customers.FavoriteShop
import com.triforce.malacprodavac.domain.repository.CustomerRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetFavShop(
    private val repository: CustomerRepository
) {

    suspend operator fun invoke(id: Int): Flow<Resource<List<FavoriteShop>>> {
        return repository.getFavoriteShops(id, true)
    }
}