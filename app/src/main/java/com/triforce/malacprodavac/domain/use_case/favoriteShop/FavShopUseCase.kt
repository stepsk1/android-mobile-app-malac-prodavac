package com.triforce.malacprodavac.domain.use_case.favoriteShop

data class FavShopUseCase(
    val getFavShops: GetFavShop,
    val deleteFavShop: DeleteFavShop,
    val addFavShop: AddFavShop
)
