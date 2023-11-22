package com.triforce.malacprodavac.presentation.FavProducts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {
                viewModel.onEvent(FavoriteEvent.AddFavProduct)
            },
            colors = ButtonDefaults.buttonColors(MP_White)
        ) {
            Text(
                text = "Dodaj omiljeni proizvod",
                color = MP_Green,
                style = MaterialTheme.typography.body1
            )
        }
    }
}