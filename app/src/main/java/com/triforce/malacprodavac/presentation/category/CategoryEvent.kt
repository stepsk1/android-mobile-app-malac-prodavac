package com.triforce.malacprodavac.presentation.category

sealed class CategoryEvent {

    object ToggleFavouriteProduct: CategoryEvent()
    data class SearchQueryChange(val query: String): CategoryEvent()
}