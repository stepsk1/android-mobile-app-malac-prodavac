package com.triforce.malacprodavac.domain.use_case.couriers

import com.triforce.malacprodavac.domain.repository.CourierRepository

data class GetCourier(val repository: CourierRepository) {
    suspend operator fun invoke(courierId: Int) = repository.getCourier(courierId)
}
