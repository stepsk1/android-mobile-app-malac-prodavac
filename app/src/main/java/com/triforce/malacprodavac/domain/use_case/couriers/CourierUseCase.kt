package com.triforce.malacprodavac.domain.use_case.couriers

data class CourierUseCase(
    val createCourier: CreateCourier,
    val getCouriers: GetCouriers,
    val getCourier: GetCourier
)
