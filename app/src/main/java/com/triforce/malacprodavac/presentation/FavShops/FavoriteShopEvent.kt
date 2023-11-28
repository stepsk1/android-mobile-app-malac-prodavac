package com.triforce.malacprodavac.presentation.FavShops

sealed class FavoriteShopEvent {

    object AddFavShop: FavoriteShopEvent()
    object GetFavShops: FavoriteShopEvent()
    object DeleteFavShop: FavoriteShopEvent()
}