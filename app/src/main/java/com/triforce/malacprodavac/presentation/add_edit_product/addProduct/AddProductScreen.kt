package com.triforce.malacprodavac.presentation.add_edit_product.addProduct

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.presentation.add_edit_product.addProduct.components.AddProductBottomBar
import com.triforce.malacprodavac.presentation.add_edit_product.addProduct.components.AddProductScreenContent
import com.triforce.malacprodavac.presentation.store.components.GoBackComp


@Composable
fun AddProductScreen(
    navController: NavController,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            GoBackComp("Novi proizvod", navController, true)
        },
        content = { padding ->
            AddProductScreenContent(navController, viewModel, padding)
        },
        bottomBar = {
            AddProductBottomBar(navController, viewModel)
        }
    )
}