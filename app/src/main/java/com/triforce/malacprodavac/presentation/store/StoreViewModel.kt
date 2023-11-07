package com.triforce.malacprodavac.presentation.store

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    var state by mutableStateOf(StoreState())

    private fun getCategories(category: Category, fetchFromRemote: Boolean) {
        viewModelScope.launch {
            categoryRepository.getCategories(fetchFromRemote)
            state.copy(
                categories = categoryRepository.getCategories(fetchFromRemote) as List<Locale.Category>,

            )
        }
    }
}