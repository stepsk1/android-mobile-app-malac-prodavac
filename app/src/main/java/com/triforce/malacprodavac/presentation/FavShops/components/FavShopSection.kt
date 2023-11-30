package com.triforce.malacprodavac.presentation.FavShops.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopViewModel

@Composable
fun FavShopSection(
    favoriteShops: List<FavoriteShop>,
    viewModel: FavoriteShopViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                bottom = 50.dp
            ), // 130 dp bottom padding because navigation and total price
            modifier = Modifier
                .requiredHeight(530.dp)
                .padding(top = 20.dp)
        ) {

            items(favoriteShops.size) {// how many items do we have
                // define one of items
                FavShopItem(
                    favoriteShop = favoriteShops[it],
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}
