package com.triforce.malacprodavac.domain.use_case.couriers

import com.triforce.malacprodavac.domain.model.CreateCourier
import com.triforce.malacprodavac.domain.repository.CourierRepository

data class CreateCourier(val repository: CourierRepository) {
    suspend operator fun invoke(createCourier: CreateCourier) =
        repository.registerCourier(createCourier)
}
