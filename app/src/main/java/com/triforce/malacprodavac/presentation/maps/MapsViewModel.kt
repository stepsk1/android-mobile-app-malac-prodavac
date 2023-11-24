package com.triforce.malacprodavac.presentation.maps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.triforce.malacprodavac.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel@Inject constructor(

    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    var state by mutableStateOf(MapState())

}