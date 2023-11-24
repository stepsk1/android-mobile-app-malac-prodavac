package com.triforce.malacprodavac.presentation.maps

import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val isSpecialMap: Boolean = false

)