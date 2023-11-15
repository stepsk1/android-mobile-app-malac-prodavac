package com.triforce.malacprodavac.domain.use_case.registration

import com.triforce.malacprodavac.domain.model.Courier
import com.triforce.malacprodavac.domain.model.CreateCourier
import com.triforce.malacprodavac.domain.repository.CourierRepository
import com.triforce.malacprodavac.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class RegisterCourier(private val repository: CourierRepository) {
    suspend operator fun invoke(createCourier: CreateCourier): Flow<Resource<Courier>> {
        return repository.registerCourier(createCourier)
    }
}