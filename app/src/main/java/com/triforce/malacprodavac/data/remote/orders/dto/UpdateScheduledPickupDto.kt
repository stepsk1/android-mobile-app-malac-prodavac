package com.triforce.malacprodavac.data.remote.orders.dto

import com.triforce.malacprodavac.domain.model.UpdateScheduledPickup

data class UpdateScheduledPickupDto (
    override val accepted: Boolean?
) : UpdateScheduledPickup