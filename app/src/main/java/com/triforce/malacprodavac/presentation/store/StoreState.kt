package com.triforce.malacprodavac.presentation.store

import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Locale.Category

data class StoreState(
    val categories: List<Category> = emptyList(),
    val subCategories: List<Category>? = emptyList()
)
