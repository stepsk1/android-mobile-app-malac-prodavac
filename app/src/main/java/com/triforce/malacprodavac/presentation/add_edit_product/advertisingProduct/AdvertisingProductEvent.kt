package com.triforce.malacprodavac.presentation.add_edit_product.advertisingProduct

import android.content.Context

sealed class AdvertisingProductEvent {
    data class StartAdvertisingChanged(val start: Double) : AdvertisingProductEvent()
    data class EndAdvertisingChanged(val end: Double) : AdvertisingProductEvent()
    data class TitleChanged(val title: String) : AdvertisingProductEvent()
    data class DescChanged(val desc: String) : AdvertisingProductEvent()
    data class LocationChanged(val location: String) : AdvertisingProductEvent()
    data class Submit(val context: Context) : AdvertisingProductEvent()
}
