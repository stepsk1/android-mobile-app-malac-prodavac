package com.triforce.malacprodavac.domain.use_case.favoriteShop

import com.triforce.malacprodavac.domain.repository.CustomerRepository

class DeleteFavShop(
    private val repository: CustomerRepository
) {

    suspend operator fun invoke(id: Int, favShopId: Int) {
        repository.deleteFavoriteShop(id, favShopId)
    }
}