package com.triforce.malacprodavac.presentation.category

sealed class CategoryEvent {
    data class OrderBy(val order: Int) : CategoryEvent()
}