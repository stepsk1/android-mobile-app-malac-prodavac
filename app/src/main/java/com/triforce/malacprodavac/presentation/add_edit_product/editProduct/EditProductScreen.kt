package com.triforce.malacprodavac.presentation.add_edit_product.editProduct

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.components.EditProductBottomBar
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.components.EditProductScreenContent
import com.triforce.malacprodavac.presentation.store.components.GoBackComp

@Composable
fun EditProductScreen(
    navController: NavController,
    viewModel: EditProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            GoBackComp("Izmeni proizvod", navController, true)
        },
        content = { padding ->
            EditProductScreenContent(navController, viewModel, padding)
        },
        bottomBar = {
            EditProductBottomBar(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }
    )
}