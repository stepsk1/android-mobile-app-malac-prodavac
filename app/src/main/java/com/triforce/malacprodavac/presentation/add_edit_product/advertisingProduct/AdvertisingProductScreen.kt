package com.triforce.malacprodavac.presentation.add_edit_product.advertisingProduct

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
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
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditDropDownList
import com.triforce.malacprodavac.presentation.add_edit_product.components.AddEditTextField
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.EditProductEvent
import com.triforce.malacprodavac.presentation.cart.CartDetails.components.GoBackNoSearch
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.product.components.ProductHeroImage
import com.triforce.malacprodavac.presentation.profile.profilePrivate.components.AdvertisingProductButton
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White


@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun AdvertisingProductScreen(
    navController: NavController,
    viewModel: AdvertisingProductViewModel = hiltViewModel()
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
                "Uspešno ste oglasili proizvod!",
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
    ) { }

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .verticalScroll(state = scrollState)
            .background(MP_White)
    ) {
        LinearGradient(color1 = colorForeground, color2 = colorBackground)
        RoundedBackgroundComp(top = 250.dp, color = MP_White)

        Column {
            GoBackNoSearch(msg = "Oglasi proizvod", modifier = Modifier.clickable {
                navController.popBackStack()
            })
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

            AddEditTextField(
                label = "Naziv proizvoda",
                text = state.product?.title ?: "",
                onTextValueChange = {

                },
                placeholder = "Naziv",
                colorBackground = colorBackground,
                colorForeground = colorForeground
            )

            AddEditTextField(
                label = "Opis proizvoda",
                text = state.product?.desc ?: "",
                onTextValueChange = {

                },
                placeholder = "Opis",
                colorBackground = colorBackground,
                colorForeground = colorForeground
            )

            AddEditTextField(
                label = "Lokacija oglašavanja",
                text = state.product?.availableAt ?: "",
                onTextValueChange = {
                    viewModel.onEvent(AdvertisingProductEvent.LocationChanged(it))
                },
                placeholder = "Lokacija",
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
                    entries = listOf(7.0, 8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0,22.0),
                    selectedEntry = state.product?.availableFromHours?.toInt().toString(),
                    handleSelect = { unit ->
                        viewModel.onEvent(
                            AdvertisingProductEvent.StartAdvertisingChanged(
                                unit as Double
                            )
                        )
                    },
                    label = "Početak oglašavanja",
                    fill = false
                )

                AddEditDropDownList(
                    entries = listOf(8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0,22.0,23.0),
                    selectedEntry = state.product?.availableTillHours?.toInt().toString(),
                    handleSelect = { unit ->
                        viewModel.onEvent(
                            AdvertisingProductEvent.StartAdvertisingChanged(
                                unit as Double
                            )
                        )
                    },
                    label = "Kraj oglašavanja",
                    fill = false
                )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                AdvertisingProductButton(
                    Modifier.clickable {
                        viewModel.onEvent(AdvertisingProductEvent.Submit(context))
                    },
                    product = product,
                    navController = navController,
                    advertising = true,
                    change = true,
                    )
                Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}