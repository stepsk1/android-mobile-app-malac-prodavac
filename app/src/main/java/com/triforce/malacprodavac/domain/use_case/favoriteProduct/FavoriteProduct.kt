package com.triforce.malacprodavac.domain.use_case.favoriteProduct

data class FavoriteProduct(
    val getFavProducts: GetFavProducts,
    val deleteFavProduct: DeleteFavProduct,
    val addFavProduct: AddFavProduct
)
