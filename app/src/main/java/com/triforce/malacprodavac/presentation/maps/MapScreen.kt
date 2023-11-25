package com.triforce.malacprodavac.presentation.maps

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.triforce.malacprodavac.ui.theme.MP_Green

@Composable
fun MapScreen(

    navController: NavController,
    viewModel: MapsViewModel = hiltViewModel()

) {
    val scaffoldState = rememberScaffoldState()

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleSpecialMap)
            }) {
                Icon(
                    imageVector = if (viewModel.state.isSpecialMap) {
                        Icons.Outlined.Clear
                    } else {
                        Icons.Outlined.LocationOn
                    },
                    contentDescription = "Toggle Special map"
                )
            }
        },
        content = { padding ->
            GoogleMap(
                properties = viewModel.state.properties,
                uiSettings = uiSettings,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                onMapLongClick = {
                    viewModel.onEvent(MapEvent.OnMapLongClick(it))
                }
            ) {
                Log.d("SHOPS2", viewModel.state.shops.toString())

                viewModel.state.shops!!.forEach { shop ->
                    if ( shop.availableAtLatitude != null && shop.availableAtLongitude != null ) {
                        Log.d(
                            "MARKER",
                            "LAT ${shop.availableAtLatitude}, LONG ${shop.availableAtLongitude} ID ${shop.id}"
                        )
                        Marker(
                            position = LatLng(
                                shop.availableAtLatitude,
                                shop.availableAtLongitude
                            ),
                            title = shop.businessName,
                            snippet = if ( shop.user != null ) {shop.user.firstName + " " + shop.user.lastName } else { "" },
                            onInfoWindowLongClick = {
                                viewModel.onEvent(
                                    MapEvent.OnInfoWindowLongClick(shop)
                                )
                            },
                            onClick = {
                                it.showInfoWindow()
                                true
                            },
                            icon = BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                        )
                    }
                }
            }
        }
    )
}