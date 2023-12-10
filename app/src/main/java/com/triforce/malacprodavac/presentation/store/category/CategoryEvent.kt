package com.triforce.malacprodavac.presentation.store.category

sealed class CategoryEvent {
    data class OrderBy(val order: Int) : CategoryEvent()
}