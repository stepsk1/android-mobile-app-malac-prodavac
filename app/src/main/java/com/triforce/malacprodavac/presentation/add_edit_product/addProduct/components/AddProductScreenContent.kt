package com.triforce.malacprodavac.presentation.add_edit_product.addProduct.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement
import com.triforce.malacprodavac.presentation.add_edit_product.addProduct.AddProductEvent
import com.triforce.malacprodavac.presentation.add_edit_product.addProduct.AddProductViewModel
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditDropDownList
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.ui.theme.MP_White

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProductScreenContent(
    navController: NavController,
    viewModel: AddProductViewModel,
    padding: PaddingValues
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {}
    )

    if (state.isCreateSuccessful && state.createdProduct != null) {
        Toast
            .makeText(
                LocalContext.current,
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

    val paddingBetween = 12.dp

    Box(
        modifier = Modifier
            .padding(padding)
            .pullRefresh(pullRefreshState)
            .verticalScroll(state = scrollState)
            .background(MP_White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(630.dp)
        ) {
            Spacer(modifier = Modifier.padding(paddingBetween))
            AddEditTextField(
                label = "Naziv proizvoda",
                isError = state.titleError != null,
                text = state.title,
                onTextValueChange = {
                    viewModel.onEvent(AddProductEvent.TitleChanged(it))
                },
                placeholder = "Unesite naziv proizvoda..."
            )
            Text(state.titleError ?: "", color = Color.Red)

            AddEditTextField(
                label = "Opis proizvoda",
                text = state.desc,
                onTextValueChange = {
                    viewModel.onEvent(AddProductEvent.DescChanged(it))
                },
                placeholder = "Unesite opis proizvoda..."
            )
            Spacer(modifier = Modifier.padding(paddingBetween))

            AddEditDropDownList(
                entries = state.categories,
                selectedEntry = state.categories.firstOrNull()?.toString(),
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
            Spacer(modifier = Modifier.padding(paddingBetween))

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
            Spacer(modifier = Modifier.padding(paddingBetween))

            AddEditTextField(
                label = "Cena",
                text = state.price.toString(),
                isError = state.priceError != null,
                keyboardType = KeyboardType.Number,
                onTextValueChange = {
                    viewModel.onEvent(AddProductEvent.PriceChanged(it.toDouble()))
                },
                placeholder = "Cena..."
            )
            Text(state.priceError ?: "", color = Color.Red)
            Spacer(modifier = Modifier.padding(paddingBetween))
        }
    }
}