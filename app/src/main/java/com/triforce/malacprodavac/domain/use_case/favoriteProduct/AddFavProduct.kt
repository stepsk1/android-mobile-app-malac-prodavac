package com.triforce.malacprodavac.domain.use_case.favoriteProduct

import com.triforce.malacprodavac.data.remote.customers.dto.CreateFavoriteProductDto
import com.triforce.malacprodavac.domain.repository.CustomerRepository

class AddFavProduct (private val repository: CustomerRepository) {
    suspend operator fun invoke(customerId: Int, createFavoriteProductDto: CreateFavoriteProductDto) {
        repository.insertFavoriteProduct(customerId, createFavoriteProductDto)
    }
}