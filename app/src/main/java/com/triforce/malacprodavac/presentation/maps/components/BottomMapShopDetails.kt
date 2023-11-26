package com.triforce.malacprodavac.presentation.maps.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.domain.model.Shop
import com.triforce.malacprodavac.presentation.maps.MapState
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_White
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun BottomMapShopDetails(
    selectedShop: Shop?,
    showShopDetails: Boolean
) {
    if (selectedShop != null) {
        val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)

        if (showShopDetails) {
            LaunchedEffect(Unit) {
                sheetState.show()
            }
        }

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.logo_green),
                            contentDescription = "Malac Prodavac",
                            tint = MP_Green,
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Text(
                            text = "Malac Prodavac",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            color = MP_GreenDark,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))

                    ShopDetailItem("Naziv prodavca", selectedShop.businessName ?: "-")
                    Spacer(modifier = Modifier.height(16.dp))

                    ShopDetailItem("Otvoreno od", selectedShop.openFrom ?: "-")
                    ShopDetailItem("Zatvara se", selectedShop.openTill ?: "-")
                    Spacer(modifier = Modifier.height(16.dp))

                    ShopDetailItem("Danima od", selectedShop.openFromDays ?: "-")
                    ShopDetailItem("Do", selectedShop.openTillDays ?: "-")
                    Spacer(modifier = Modifier.height(16.dp))

                    ShopDetailItem("Lat", selectedShop.availableAtLatitude.toString())
                    ShopDetailItem("Long", selectedShop.availableAtLongitude.toString())
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        ) {
        }
    }
}

@Composable
fun ShopDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = MP_Green,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.W500,
            color = MP_Black
        )
    }
}
