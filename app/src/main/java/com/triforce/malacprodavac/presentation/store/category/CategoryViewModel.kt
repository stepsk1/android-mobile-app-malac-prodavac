package com.triforce.malacprodavac.presentation.store.category

import android.util.Log
import androidx.compose.material.Colors
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.data.services.filter.FilterOperation
import com.triforce.malacprodavac.data.services.filter.SingleFilter
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Pink_Dark
import com.triforce.malacprodavac.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class CategoryViewModel @Inject constructor(

    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    var state by mutableStateOf(CategoryState())

    private val _categoryTitle = mutableStateOf(CategoryState(

        title = "Category title..."

    ))
    val categoryTitle: State<CategoryState> = _categoryTitle

    var currentCategoryId: Int? = null

    init {
        savedStateHandle.get<Int>("categoryId")?.let { categoryId ->

            currentCategoryId = categoryId

            getProducts(true, categoryId);
        }

        savedStateHandle.get<String>("title")?.let { title ->
            _categoryTitle.value = categoryTitle.value.copy(title = title)
        }
    }

    private fun getProducts(fetchFromRemote: Boolean, categoryId: Int) {

        viewModelScope.launch {

            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(
                        SingleFilter(
                            "categoryId",
                            FilterOperation.Eq,
                            categoryId
                        )
                    ), order = null, limit = null, offset = null
                )
            )

            repository.getProducts(categoryId, fetchFromRemote, query).collect({ result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<Product>) {
                            println(result.data)
                            state = state.copy(products = result.data)
                        }
                    }
                    is Resource.Error -> {
                        Unit
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }
                }
            })
        }
    }
}