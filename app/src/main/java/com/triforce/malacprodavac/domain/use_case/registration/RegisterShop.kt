package com.triforce.malacprodavac.domain.use_case.registration

import com.triforce.malacprodavac.domain.model.CreateShop
import com.triforce.malacprodavac.domain.model.shops.Shop
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class RegisterShop(private val repository: ShopRepository) {
    suspend operator fun invoke(createShop: CreateShop): Flow<Resource<Shop>> {
        return repository.registerShop(createShop)
    }
}