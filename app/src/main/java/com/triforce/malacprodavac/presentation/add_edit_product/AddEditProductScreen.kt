package com.triforce.malacprodavac.presentation.add_edit_product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.util.enum.UserRole
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditDropDownList
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditSubmitButton
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.product.ProductHeroImage
import com.triforce.malacprodavac.presentation.product.ProductViewModel
import com.triforce.malacprodavac.presentation.registration.RegistrationFormEvent
import com.triforce.malacprodavac.presentation.registration.components.DropDownList
import com.triforce.malacprodavac.presentation.store.StoreViewModel
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.ui.theme.SpaceMedium
import com.triforce.malacprodavac.util.enum.Currency
import com.triforce.malacprodavac.util.enum.UnitOfMeasurement


@Composable
fun AddEditProductScreen(
    navController: NavController,
    viewModel: AddEditProductViewModel = hiltViewModel()
) {
    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeState = storeViewModel.state
    val categories: List<Category> = storeState.categories

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
                .height(1000.dp)
        ){
            LinearGradient(color1 = colorForeground, color2 = colorBackground)
            RoundedBackgroundComp(top = 250.dp, color = MP_White)

            Column{
                if ( product != null )
                {
                    GoBackNoSearch("Izmeni proizvod", navController)
                    ProductHeroImage()
                    Spacer(modifier = Modifier.padding(20.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .height(550.dp)
                    ) {
                        AddEditTextField(
                            label = "Naziv proizvoda",
                            text = state.title,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.TitleChanged(it))
                            },
                            placeholder = product.title,
                            colorBackground = colorBackground,
                            colorForeground = colorForeground
                        )
                        AddEditTextField(
                            label = "Opis proizvoda",
                            text = state.desc,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.DescChanged(it))
                            },
                            placeholder = product.desc,
                            colorBackground = colorBackground,
                            colorForeground = colorForeground
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    AddEditSubmitButton(navController = navController, viewModel = viewModel, isEdit = true)
                }
                else {
                    GoBackNoSearch(msg = "Dodaj proizvod", navController = navController)
                    ProductHeroImage()
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .height(600.dp)
                    ) {
                        AddEditTextField(
                            label = "Naziv proizvoda",
                            text = state.title,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.TitleChanged(it))
                            },
                            placeholder = "Unesite naziv proizvoda...",
                            colorBackground = colorBackground,
                            colorForeground = colorForeground
                        )

                        AddEditTextField(
                            label = "Opis proizvoda",
                            text = state.desc,
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.DescChanged(it))
                            },
                            placeholder = "Unesite opis proizvoda...",
                            colorBackground = colorBackground,
                            colorForeground = colorForeground
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            AddEditDropDownList(
                                entries = enumValues<UnitOfMeasurement>().toList(),
                                handleSelect = { unit ->
                                    viewModel.onEvent(
                                        AddEditProductEvent.UnitOfMeasurementChanged(
                                            unit as UnitOfMeasurement
                                        )
                                    )
                                },
                                label = "Mera",
                                fill = false
                            )

                            AddEditDropDownList(
                                entries = enumValues<Currency>().toList(),
                                handleSelect = { currency ->
                                    viewModel.onEvent(
                                        AddEditProductEvent.CurrencyChanged(
                                            currency as Currency)
                                    )
                                },
                                label = "Valuta",
                                fill = false
                            )
                        }

                        AddEditTextField(
                            label = "Cena",
                            text = state.price.toString(),
                            onTextValueChange = {
                                viewModel.onEvent(AddEditProductEvent.PriceChanged(it.toDouble()))
                            },
                            placeholder = "Cena...",
                            colorBackground = colorBackground,
                            colorForeground = colorForeground
                        )

                        AddEditDropDownList(
                            entries = enumValues<Currency>().toList(),
                            handleSelect = { categoryId ->
                                viewModel.onEvent(
                                    AddEditProductEvent.CurrencyChanged(
                                        categoryId as Currency)
                                )
                            },
                            label = "Kategorija",
                            fill = true
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    AddEditSubmitButton(navController = navController, viewModel = viewModel, isEdit = false)
                }
            }
        }
    }
}