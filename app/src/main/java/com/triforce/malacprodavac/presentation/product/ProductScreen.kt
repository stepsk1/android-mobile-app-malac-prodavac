package com.triforce.malacprodavac.presentation.product

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.product.components.ProductBottomBar
import com.triforce.malacprodavac.presentation.product.components.ProductScreenContent
import com.triforce.malacprodavac.presentation.store.components.GoBackComp

@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    viewModelFavProduct: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            GoBackComp("Malac Pijaca", navController, true)
        },
        bottomBar = {
            ProductBottomBar(navController, viewModel, viewModelFavProduct)
        },
        content = { padding ->
            ProductScreenContent(navController, viewModel)
        }
    )
}