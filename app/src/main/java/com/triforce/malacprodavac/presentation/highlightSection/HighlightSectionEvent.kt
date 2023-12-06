package com.triforce.malacprodavac.presentation.highlightSection

sealed class HighlightSectionEvent {
    data class OrderBy(val order: Int) : HighlightSectionEvent()
}