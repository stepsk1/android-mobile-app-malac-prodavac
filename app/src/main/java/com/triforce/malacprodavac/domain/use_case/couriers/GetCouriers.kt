package com.triforce.malacprodavac.domain.use_case.couriers

import com.triforce.malacprodavac.domain.repository.CourierRepository

data class GetCouriers(val repository: CourierRepository) {
    suspend operator fun invoke(filters: MutableMap<String, String>) =
        repository.getCouriers(filters)
}
