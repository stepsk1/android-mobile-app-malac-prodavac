package com.triforce.malacprodavac.presentation.FavShops

sealed class FavoriteShopEvent {
    data class AddFavShop(val shopId: Int) : FavoriteShopEvent()
    object GetFavShops : FavoriteShopEvent()
    data class DeleteFavShop(val favoriteShopId: Int) : FavoriteShopEvent()
}