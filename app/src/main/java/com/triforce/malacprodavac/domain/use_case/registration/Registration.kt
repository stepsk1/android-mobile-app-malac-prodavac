package com.triforce.malacprodavac.domain.use_case.registration

data class Registration(
    val createCustomer: RegisterCustomer,
    val createCourier: RegisterCourier,
    val createShop: RegisterShop
)
