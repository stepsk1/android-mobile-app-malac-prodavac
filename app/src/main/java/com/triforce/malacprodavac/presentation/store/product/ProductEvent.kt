package com.triforce.malacprodavac.presentation.store.product

import com.triforce.malacprodavac.presentation.store.category.CategoryEvent

sealed class ProductEvent {
    object ToggleFavouriteProduct: ProductEvent()

}