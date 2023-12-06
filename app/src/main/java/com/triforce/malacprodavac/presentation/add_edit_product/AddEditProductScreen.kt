package com.triforce.malacprodavac.presentation.add_edit_product

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditDropDownList
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditSubmitButton
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.product.components.ProductHeroImage
import com.triforce.malacprodavac.presentation.store.StoreViewModel
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White
import com.triforce.malacprodavac.util.enum.Currency
import com.triforce.malacprodavac.util.enum.UnitOfMeasurement


@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
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

    var heightScreen = 1050.dp

    if (product == null) {
        heightScreen = 980.dp
        colorBackground = MP_GreenDark
        colorForeground = MP_Green
    } else {
        colorBackground = MP_Orange_Dark
        colorForeground = MP_Orange
    }

    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {}
    )

    if (state.successful) {
        LaunchedEffect(key1 = true) {
            navController.navigate(Screen.PrivateProfile.route)
        }
    }

    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
    val permissionState = rememberPermissionState(
        permission = permission
    )

    val launcher = LocalContext.current.let { it ->
        rememberLauncherForActivityResult(
            ActivityResultContracts.PickMultipleVisualMedia(5)
        ) { uris ->
            if (uris.isNotEmpty()) {
                viewModel.onEvent(AddEditProductEvent.ChangeProductImages(it, uris))
            }
        }
    }
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .verticalScroll(state = scrollState)
            .background(MP_White)
            .height(heightScreen)
    ) {
        LinearGradient(color1 = colorForeground, color2 = colorBackground)
        RoundedBackgroundComp(top = 250.dp, color = MP_White)

        Column {
            if (product != null) {
                GoBackNoSearch("Izmeni proizvod", navController)
                ProductHeroImage(modifier = Modifier.clickable {
                    if (!permissionState.status.isGranted) {
                        permissionState.launchPermissionRequest()
                    } else {
                        launcher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                })
                Spacer(modifier = Modifier.padding(20.dp))
                Column(
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
                        onTextValueChange = {
                            viewModel.onEvent(AddEditProductEvent.PriceChanged(it.toDouble()))
                        },
                        placeholder = product.price.toString(),
                        colorBackground = colorBackground,
                        colorForeground = colorForeground
                    )

                    AddEditDropDownList(
                        entries = enumValues<Currency>().toList(),
                        handleSelect = { categoryId ->
                            viewModel.onEvent(
                                AddEditProductEvent.CurrencyChanged(
                                    categoryId as Currency
                                )
                            )
                        },
                        label = "Kategorija",
                        fill = true
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                AddEditSubmitButton(
                    navController = navController,
                    viewModel = viewModel,
                    isEdit = true
                )
            } else {
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
                                    categoryId as Currency
                                )
                            )
                        },
                        label = "Kategorija",
                        fill = true
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                AddEditSubmitButton(
                    navController = navController,
                    viewModel = viewModel,
                    isEdit = false
                )
            }
        }
    }
}