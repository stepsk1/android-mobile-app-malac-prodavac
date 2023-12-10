package com.triforce.malacprodavac.presentation.FavProducts

sealed class FavoriteEvent {
    data class AddFavProduct(val productId: Int): FavoriteEvent()
    object GetFavProducts: FavoriteEvent()
    data class DeleteFavProduct(val productId: Int): FavoriteEvent()
}
