package com.triforce.malacprodavac.presentation.addProduct

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditDropDownList
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditSubmitButton
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_White


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProductScreen(
    navController: NavController,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state = viewModel.state

    val colorBackground = MP_GreenDark
    val colorForeground = MP_Green


    val scrollState = rememberScrollState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {}
    )

    if (state.isCreateSuccessful && state.createdProduct != null) {
        Toast
            .makeText(
                context,
                "Uspešno je kreiran novi proizvod",
                Toast.LENGTH_LONG
            )
            .show()
        LaunchedEffect(Unit) {
            navController.navigate(Screen.ProductScreen.route + "?productId=${state.createdProduct.id}") {
                popUpTo(Screen.AddProduct.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .verticalScroll(state = scrollState)
            .background(MP_White)
    ) {
        LinearGradient(color1 = colorForeground, color2 = colorBackground)
        RoundedBackgroundComp(top = 250.dp, color = MP_White)

        Column {
            GoBackNoSearch(msg = "Dodaj proizvod", modifier = Modifier.clickable {
                navController.popBackStack()
                //navController.navigate(Screen.HomeScreen.route)
            })
//                ProductHeroImage()
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .height(600.dp)
            ) {
                AddEditTextField(
                    label = "Naziv proizvoda",
                    isError = state.titleError != null,
                    text = state.title,
                    onTextValueChange = {
                        viewModel.onEvent(AddProductEvent.TitleChanged(it))
                    },
                    placeholder = "Unesite naziv proizvoda...",
                    colorBackground = colorBackground,
                    colorForeground = colorForeground
                )
                Text(state.titleError ?: "", color = Color.Red)

                AddEditTextField(
                    label = "Opis proizvoda",
                    text = state.desc,
                    onTextValueChange = {
                        viewModel.onEvent(AddProductEvent.DescChanged(it))
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
                        selectedEntry = state.unitOfMeasurement.toString(),
                        handleSelect = { unit ->
                            viewModel.onEvent(
                                AddProductEvent.UnitOfMeasurementChanged(
                                    unit as UnitOfMeasurement
                                )
                            )
                        },
                        label = "Mera",
                        fill = false
                    )

                    AddEditDropDownList(
                        entries = enumValues<Currency>().toList(),
                        selectedEntry = state.currency.toString(),
                        handleSelect = { currency ->
                            viewModel.onEvent(
                                AddProductEvent.CurrencyChanged(
                                    currency as Currency
                                )
                            )
                        },
                        label = "Valuta",
                        fill = false
                    )
                }

                AddEditTextField(
                    label = "Cena",
                    text = state.price.toString(),
                    isError = state.priceError != null,
                    keyboardType = KeyboardType.Number,
                    onTextValueChange = {
                        viewModel.onEvent(AddProductEvent.PriceChanged(it.toDouble()))
                    },
                    placeholder = "Cena...",
                    colorBackground = colorBackground,
                    colorForeground = colorForeground
                )
                Text(state.priceError ?: "", color = Color.Red)

                AddEditDropDownList(
                    entries = state.categories,
                    selectedEntry = if (state.categories.isEmpty()) "" else state.categories.find {
                        it.id == state.categoryId
                    }
                        .toString(),
                    handleSelect = { category ->
                        viewModel.onEvent(
                            AddProductEvent.CategoryIdChanged(
                                (category as Category).id
                            )
                        )
                    },
                    label = "Kategorija",
                    fill = true
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            AddEditSubmitButton(
                Modifier.clickable {
                    viewModel.onEvent(AddProductEvent.Submit)
                },
                isEdit = false
            )
        }
    }
}