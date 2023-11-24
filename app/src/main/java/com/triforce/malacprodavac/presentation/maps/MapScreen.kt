package com.triforce.malacprodavac.presentation.maps

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

@Composable
fun MapScreen(

    navController: NavController,
    viewModel: MapsViewModel = hiltViewModel()

) {
    val state = viewModel.state
    val uiSettings = remember{
        MapUiSettings(zoomControlsEnabled = false)
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = if (state.isSpecialMap) {
                        Icons.Outlined.Clear } else { Icons.Outlined.LocationOn },
                    contentDescription = "Toggle Special map"
                )
            }
        }
    ) {
        GoogleMap(
            properties = state.properties,
            uiSettings = uiSettings,
            modifier = Modifier
                .fillMaxSize(),
            onMapLongClick = {

            }
        ) {

        }
    }


}