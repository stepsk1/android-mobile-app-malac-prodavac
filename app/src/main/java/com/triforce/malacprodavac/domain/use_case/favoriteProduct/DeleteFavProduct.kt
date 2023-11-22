package com.triforce.malacprodavac.domain.use_case.favoriteProduct

import com.triforce.malacprodavac.domain.repository.CustomerRepository

class DeleteFavProduct(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(id: Int, favProductId: Int) {
        repository.deleteFavoriteProduct(id, favProductId)
    }
}