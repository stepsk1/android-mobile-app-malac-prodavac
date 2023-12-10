package com.triforce.malacprodavac.presentation.add_edit_product.advertisingProduct

import android.net.Uri
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.EditProductEvent

sealed class AdvertisingProductEvent {
    data class ChangeProductImages(val imageUris: List<Uri>) :
        AdvertisingProductEvent()

    data class StartAdvertisingChanged(val start: Int) : AdvertisingProductEvent()
    data class EndAdvertisingChanged(val end: Int) : AdvertisingProductEvent()
    data class TitleChanged(val title: String) : AdvertisingProductEvent()
    data class DescChanged(val desc: String) : AdvertisingProductEvent()
}
