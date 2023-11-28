package com.triforce.malacprodavac.domain.use_case.favoriteShop

import com.triforce.malacprodavac.data.repository.customers.favoriteShops.dto.CreateFavoriteShopDto
import com.triforce.malacprodavac.domain.repository.CustomerRepository

class AddFavShop (private val repository: CustomerRepository){
    suspend operator fun invoke(customerId: Int, createFavoriteShopDto: CreateFavoriteShopDto) {
        repository.insertFavoriteShop(customerId, createFavoriteShopDto)
    }
}