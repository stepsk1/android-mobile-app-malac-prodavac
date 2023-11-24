package com.triforce.malacprodavac.presentation.product

sealed class ProductEvent {
    object ToggleFavouriteProduct: ProductEvent()
    object buyProduct: ProductEvent()
    object favoriteProduct: ProductEvent()
    object removeFavoriteProduct: ProductEvent()
}