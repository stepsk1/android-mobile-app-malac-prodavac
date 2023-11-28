package com.triforce.malacprodavac.presentation.profile.profilePublic

import com.triforce.malacprodavac.domain.model.Shop

object FavoriteShopObject {
    var favoriteShopId: Int = 1
    var favoriteShop: Shop? = null
    var favShops: MutableList<Shop?> = mutableListOf()
}