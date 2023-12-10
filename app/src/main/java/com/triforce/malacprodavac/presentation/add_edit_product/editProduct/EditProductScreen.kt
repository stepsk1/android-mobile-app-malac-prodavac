package com.triforce.malacprodavac.presentation.add_edit_product.editProduct

import android.Manifest
import android.os.Build
import android.widget.Toast
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
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.domain.util.enum.Currency
import com.triforce.malacprodavac.domain.util.enum.UnitOfMeasurement
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditDropDownList
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditSubmitButton
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.product.components.ProductHeroImage
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun EditProductScreen(
    navController: NavController,
    viewModel: EditProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state = viewModel.state
    val product = state.product

    val colorBackground = MP_Orange_Dark
    val colorForeground = MP_Orange

    val scrollState = rememberScrollState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {}
    )

    if (state.isUpdateSuccessful) {
        Toast
            .makeText(
                context,
                "Uspešno ste izmenili proizvod!",
                Toast.LENGTH_LONG
            )
            .show()
    }


    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
    val permissionState = rememberPermissionState(
        permission = permission
    )

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(5)
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onEvent(EditProductEvent.ChangeProductImages(uris))
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
            GoBackNoSearch(msg = "Izmeni proizvod", navController = navController)
            ProductHeroImage(
                modifier = Modifier.clickable {
                    if (!permissionState.status.isGranted) {
                        permissionState.launchPermissionRequest()
                    } else {
                        launcher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                },
                imageUrl = state.thumbUrl
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .height(600.dp)
            ) {
                AddEditTextField(
                    label = "Naziv proizvoda",
                    isError = state.titleError != null,
                    text = state.product?.title ?: "",
                    onTextValueChange = {
                        viewModel.onEvent(EditProductEvent.TitleChanged(it))
                    },
                    placeholder = "Naziv",
                    colorBackground = colorBackground,
                    colorForeground = colorForeground
                )
                Text(state.titleError ?: "", color = Color.Red)

                AddEditTextField(
                    label = "Opis proizvoda",
                    text = state.product?.desc ?: "",
                    onTextValueChange = {
                        viewModel.onEvent(EditProductEvent.DescChanged(it))
                    },
                    placeholder = "Opis",
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
                        selectedEntry = state.product?.unitOfMeasurement?.toString(),
                        handleSelect = { unit ->
                            viewModel.onEvent(
                                EditProductEvent.UnitOfMeasurementChanged(
                                    unit as UnitOfMeasurement
                                )
                            )
                        },
                        label = "Mera",
                        fill = false
                    )

                    AddEditDropDownList(
                        entries = enumValues<Currency>().toList(),
                        selectedEntry = state.product?.currency?.toString(),
                        handleSelect = { currency ->
                            viewModel.onEvent(
                                EditProductEvent.CurrencyChanged(
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
                    isError = state.priceError != null,
                    text = product?.price.toString(),
                    onTextValueChange = {
                        viewModel.onEvent(EditProductEvent.PriceChanged(it.toDouble()))
                    },
                    placeholder = "0.00",
                    colorBackground = colorBackground,
                    colorForeground = colorForeground
                )
                Text(state.priceError ?: "", color = Color.Red)


                AddEditDropDownList(
                    entries = state.categories,
                    selectedEntry = state.categories.find { it.id == state.product?.categoryId }
                        ?.toString(),
                    handleSelect = { category ->
                        viewModel.onEvent(
                            EditProductEvent.CategoryIdChanged(
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
                    viewModel.onEvent(EditProductEvent.Submit(context))
                },
                isEdit = true
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}