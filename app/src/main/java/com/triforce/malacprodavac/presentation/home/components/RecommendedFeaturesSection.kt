package com.triforce.malacprodavac.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.ui.theme.MP_Black

@Composable
fun RecommendedFeaturesSection(
    navController: NavController,
    features: List<Feature>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Podržite lokalnu ekonomiju",
            style = MaterialTheme.typography.h5,
            color = MP_Black,
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 7.5.dp,
                end = 7.5.dp,
                bottom = 100.dp
            ), // 100 dp bottom padding because navigation
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(features.size) {// how many items do we have
                // define one of items
                RecommendedFeatureItem(
                    navController = navController,
                    feature = features[it]
                )
            }
        }
    }
}