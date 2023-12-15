package com.triforce.malacprodavac.domain.util.enum


enum class MessageEventType(val type: String) {
    CourierInAreaForCustomer("courier_in_area_customer"),
    CourierInAreaForShop("courier_in_area_shop"),
    NewProductFromFavoriteShop("new_product_favorite_shop"),
    AvailableAgainFromFavoriteShop("available_again_from_shop"),
    ProductsAvailableAtNewLocation("products_at_new_location"),
    PickupScheduled("scheduled_pickup");

    companion object {
        infix fun from(type: String): MessageEventType =
            MessageEventType.values().first { it.type == type }
    }

}