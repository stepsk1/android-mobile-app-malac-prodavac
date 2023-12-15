package com.triforce.malacprodavac.presentation.add_edit_product.addProductImage.components

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.EditProductEvent
import com.triforce.malacprodavac.presentation.add_edit_product.editProduct.EditProductViewModel
import com.triforce.malacprodavac.presentation.product.components.ProductHeroImage
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_White

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun AddProductImageContentScreen(
    navController: NavController,
    viewModel: EditProductViewModel,
    padding: PaddingValues
) {
    val context = LocalContext.current
    val state = viewModel.state

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {}
    )

    if (state.isUpdateSuccessful && state.product != null) {
        Toast
            .makeText(
                context,
                "Uspešno ste napravili proizvod!",
                Toast.LENGTH_LONG
            )
            .show()
        LaunchedEffect(Unit) {
            navController.navigate(Screen.ProductScreen.route + "?productId=${state.product.id}") {
                popUpTo(Screen.AddProduct.route) {
                    inclusive = true
                }
            }
        }
    }


    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE
    val permissionState = rememberPermissionState(permission = permission)

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(5)
    ) { uris ->
        if (uris.isNotEmpty()) {
            viewModel.onEvent(EditProductEvent.ChangeProductImages(uris))
        }
    }

    val paddingBetween = 12.dp

    Box(
        modifier = Modifier
            .padding(padding)
            .pullRefresh(pullRefreshState)
            .background(MP_White)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .shadow(
                    elevation = 10.dp,
                    spotColor = MP_Black,
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth(0.9f)
                .background(MP_White)
                .clip(RoundedCornerShape(20.dp))
                .padding(horizontal = 20.dp, vertical = 30.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Izaberi sliku\nproizvoda",
                    style = MaterialTheme.typography.h6,
                    color = MP_Black,
                    fontWeight = FontWeight.W400,
                )
                Icon(
                    imageVector = Icons.Default.AddAPhoto,
                    contentDescription = "add_photo",
                    tint = MP_GreenDark,
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.padding(paddingBetween))
            ProductHeroImage(
                modifier = Modifier.clickable {
                    if (!permissionState.status.isGranted)
                        permissionState.launchPermissionRequest()
                    else
                        launcher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                },
                imageUrl = state.thumbUrl
            )
        }
    }
}