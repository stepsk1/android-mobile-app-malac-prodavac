package com.triforce.malacprodavac.presentation.add_edit_product

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.components.ProductAmount
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.components.ShowHighlightSectionComp
import com.triforce.malacprodavac.presentation.product.ProductDetails
import com.triforce.malacprodavac.presentation.product.ProductHeroImage
import com.triforce.malacprodavac.presentation.product.ProductViewModel
import com.triforce.malacprodavac.presentation.product.ShowFavouriteAddToCart
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_Pink_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun AddEditProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {

    var state = viewModel.state

    val product = state.product

    var colorBackground = MP_White
    var colorForeground = MP_White

    if (product != null) {

    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .background(MP_White)
            .height(800.dp)
    ) {
        LinearGradient(color1 = colorForeground, color2 = colorBackground)
        RoundedBackgroundComp(top = 250.dp, color = MP_White)

        Column {
            GoBackComp("Malac Pijaca", navController)
            ProductHeroImage()
            if (product != null) {

                Spacer(modifier = Modifier.padding(16.dp))

                ProductDetails(product = product)

                Spacer(modifier = Modifier.padding(16.dp))

                ShowHighlightSectionComp(
                    navController = navController,
                    products = listOf( Product(1,2,3,true,99.0,"RSD",9.0,null,null,null,null,"RSD","Prsuta 100g", "", null, null,"","",null,null), Product(1,2,3,true,99.0,"RSD",9.0,null,null,null,null,"RSD","Prsuta 100g", "", null, null,"","",null,null)),
                    title = "Više proizvoda od prodavca",
                    route = Screen.HighlightSection.route
                )

                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    ShowFavouriteAddToCart(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}