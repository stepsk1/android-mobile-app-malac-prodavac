package com.triforce.malacprodavac.presentation.product

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
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {

    var state = viewModel.state

    val product = state.product

    var colorBackground = MP_White
    var colorForeground = MP_White

    if (product != null) {
        if (product.categoryId % 3 == 1) {
            colorBackground = MP_GreenDark
            colorForeground = MP_Green
        } else if (product.categoryId % 3 == 2) {
            colorBackground = MP_Pink_Dark
            colorForeground = MP_Pink
        } else {
            colorBackground = MP_Orange_Dark
            colorForeground = MP_Orange
        }
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
                        //.background(MP_Pink)
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

@Composable
fun ProductHeroImage(

) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .background(MP_White, RoundedCornerShape(10.dp))
            .padding(35.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Malac Prodavac",
            tint = MP_Gray,
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Composable
fun ProductDetails(
    product: Product
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 20.dp
                )
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.h5,
                color = MP_Black,
                fontWeight = FontWeight.W500
            )
            Text(
                text = product.price.toString() + " rsd",
                style = MaterialTheme.typography.h5,
                color = MP_Green,
                fontWeight = FontWeight.W500
            )
        }
        Text(
            text = product.desc,
            style = MaterialTheme.typography.body2,
            color = Color.Gray,
            softWrap = true
        )
    }
}

@Composable
fun ShowFavouriteAddToCart(
    navController: NavController,
    viewModel: ProductViewModel
) {
    fun addToBuyedProducts(item: ProductAmount) {
        BuyedProducts.listOfBuyedProducts.add(item)
    }

    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 25.dp,
                start = 5.dp,
                end = 5.dp
            )
            .shadow(
                elevation = 10.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MP_White)
            .padding(
                vertical = 10.dp,
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "FavoriteBorder",
            tint = MP_Pink,
            modifier = Modifier
                .size(50.dp)
        )

        Text(
            text = "Dodaj u korpu",
            style = MaterialTheme.typography.h5,
            color = MP_White,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    if (viewModel.state.isBuyed == false) {
                        viewModel.onEvent(ProductEvent.buyProduct)
                        viewModel.state.product?.let { addToBuyedProducts(ProductAmount(it)) }

                        Toast
                            .makeText(
                                context,
                                "Uspešno dodat proizvod u korpu",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Proizvod se već nalazi u korpi",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }
                .clip(RoundedCornerShape(20.dp))
                .background(MP_Pink)
                .width(width = 250.dp)
                .padding(vertical = 10.dp)
        )
    }
}