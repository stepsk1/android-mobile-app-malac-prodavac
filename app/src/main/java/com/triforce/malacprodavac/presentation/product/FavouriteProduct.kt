package com.triforce.malacprodavac.presentation.product

import com.triforce.malacprodavac.domain.model.Product

object FavouriteProduct {
    var favouriteProductId: Int = 1
    var favouriteProduct: Product? = null
    var favProducts: MutableList<Product?> = mutableListOf()
}