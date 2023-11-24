package com.triforce.malacprodavac.presentation.maps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.triforce.malacprodavac.data.services.filter.Filter
import com.triforce.malacprodavac.data.services.filter.FilterBuilder
import com.triforce.malacprodavac.data.services.filter.FilterOperation
import com.triforce.malacprodavac.data.services.filter.SingleFilter
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.domain.repository.ProductRepository
import com.triforce.malacprodavac.domain.repository.ShopRepository
import com.triforce.malacprodavac.domain.util.Resource
import com.triforce.malacprodavac.presentation.maps.styles.MapStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(

    private val repository: ShopRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        getShops(true)
    }

    fun onEvent(event: MapEvent){
        when(event){
            MapEvent.ToggleSpecialMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isSpecialMap) {
                            null
                        } else {
                            MapStyleOptions(MapStyle.json)
                        },
                    ),
                    isSpecialMap = !state.isSpecialMap
                )
            }

            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {

                }
            }

            is MapEvent.OnMapLongClick -> {
                // TODO() Implement event to select shop position
            }
        }
    }

    private fun getShops(fetchFromRemote: Boolean, searchText: String = "") {

        viewModelScope.launch {

            val query = FilterBuilder.buildFilterQueryMap(
                Filter(
                    filter = listOf(

                    ), order = null, limit = null, offset = null
                )
            )

            repository.getShops(fetchFromRemote, query).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        if (result.data is List<Shop>) {
                            state = state.copy(shops = result.data)
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
            }
        }
    }
}