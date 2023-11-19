package com.triforce.malacprodavac.presentation.add_edit_product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.product.ProductHeroImage
import com.triforce.malacprodavac.presentation.product.ProductViewModel
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.ui.theme.SpaceMedium


@Composable
fun AddEditProductScreen(
    navController: NavController,
    viewModel: AddEditProductViewModel = hiltViewModel()
) {
    var state = viewModel.state
    val product = state.product

    var colorBackground = MP_White
    var colorForeground = MP_White

    if ( product == null ){
        colorBackground = MP_GreenDark
        colorForeground = MP_Green
    } else {
        colorBackground = MP_Orange_Dark
        colorForeground = MP_Orange
    }

    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isLoading
    )

    if ( state.successful ) {
        LaunchedEffect(key1 = true){
            navController.navigateUp()
        }
    }

    SwipeRefresh(state = swipeRefreshState, onRefresh = {}) {
        Box(
            modifier = Modifier
                .verticalScroll(state = scrollState)
                .background(MP_White)
                .height(800.dp)
        ){
            LinearGradient(color1 = colorForeground, color2 = colorBackground)
            RoundedBackgroundComp(top = 250.dp, color = MP_White)

            Column{
                if ( product != null )
                {
                    GoBackNoSearch("Izmeni proizvod", navController)
                    ProductHeroImage()
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        AddEditTextField(
                            text = state.title,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.TitleChanged(it))
                            },
                            placeholder = product.title
                        )
                        AddEditTextField(
                            text = state.desc,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.DescChanged(it))
                            },
                            placeholder = product.desc
                        )
                    }
                }
                else {
                    GoBackNoSearch(msg = "Dodaj proizvod", navController = navController)
                    ProductHeroImage()
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        AddEditTextField(
                            text = state.title,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.TitleChanged(it))
                            },
                            placeholder = "Naziv proizvoda"
                        )
                        AddEditTextField(
                            text = state.desc,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.DescChanged(it))
                            },
                            placeholder = "Opis proizvoda"
                        )
                    }
                }
            }
        }
    }
}