package com.triforce.malacprodavac.presentation.store.product

sealed class ProductEvent {
    object ToggleFavouriteProduct: ProductEvent()
    object buyProduct: ProductEvent()
}