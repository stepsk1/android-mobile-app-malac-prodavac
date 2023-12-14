package com.triforce.malacprodavac.presentation.notifications

sealed class NotificationDataType {
    data class CourierInAreaForCustomer(val title: String, val shops: String) :
        NotificationDataType()

    data class CourierInAreaForShop(
        val title: String,
        val text: String = "Dostavite svoje porudžbine!",
    ) : NotificationDataType()

    data class NewProductFromFavoriteShop(val title: String, val product: String) :
        NotificationDataType()
    data class AvailableAgainFromFavoriteShop(val title: String, val product: String) :
        NotificationDataType()

    data class ProductsAvailableAtNewLocation(val title: String, val location: String?) :
        NotificationDataType()


    data class PickupScheduled(val title: String, val text: String = "Odgovorite na zahtev!") :
        NotificationDataType()

}