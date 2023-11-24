package com.triforce.malacprodavac.presentation.maps

import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.presentation.maps.styles.MapStyle

data class MapState(
    val properties: MapProperties = MapProperties(),
    val isSpecialMap: Boolean = false,

    val shops: List<Shop>? = emptyList(),
    val isLoading: Boolean = false,
)