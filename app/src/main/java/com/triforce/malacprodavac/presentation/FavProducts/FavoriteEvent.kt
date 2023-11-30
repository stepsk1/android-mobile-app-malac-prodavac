package com.triforce.malacprodavac.presentation.FavProducts

sealed class FavoriteEvent {
    object AddFavProduct: FavoriteEvent()
   // object GetUser: FavoriteEvent()
    object GetFavProducts: FavoriteEvent()
    object DeleteFavProduct: FavoriteEvent()
}
