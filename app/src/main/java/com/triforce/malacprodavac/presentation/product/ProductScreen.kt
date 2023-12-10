package com.triforce.malacprodavac.presentation.product

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.product.components.ProductBottomBar
import com.triforce.malacprodavac.presentation.product.components.ProductScreenContent
import com.triforce.malacprodavac.presentation.product.components.ShowFavouriteAddToCart
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel(),
    viewModelFavProduct: FavoriteViewModel = hiltViewModel()
){
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