package com.triforce.malacprodavac.presentation.store.category

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.presentation.store.components.FilterSortComp
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StoreCategoryScreen(

    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val productsList: List<Product>? = state.products

    val titleState = viewModel.categoryTitle.value

    var colorBackground = MP_Orange_Dark
    var colorForeground = MP_Orange

    if ( viewModel.currentCategoryId!! % 3 == 1 ) {
        colorBackground = MP_GreenDark
        colorForeground = MP_Green
    }
    else if ( viewModel.currentCategoryId!! % 3 == 2 ) {
        colorBackground = MP_Pink_Dark
        colorForeground = MP_Pink
    }

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ){
        LinearGradient(color1 = colorForeground, color2 = colorBackground )
        Surface (
            color = MP_White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
                .padding(top = 250.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
        ){

        }
        Column {
            GoBackComp("Malac Pijaca", navController)
            CategorySectionHeader(titleState.title, "Podržite zajednicu, podržavajte lokalno preduzetništvo. Vaša podrška čini razliku!", colorBackground)
            FilterSortComp(navController)
            ShowcaseProducts(
                products = productsList,
                navController
            )
        }
    }
}

@Composable
fun CategoriesSection(categories: List<String>) {

}

@Composable
fun CategorySectionHeader(
    title: String,
    sub: String,
    colorBackground: Color
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .background(colorBackground, RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        Column (
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MP_White,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(0.5F)
            )
            Text(
                text = sub,
                style = MaterialTheme.typography.body2,
                color = MP_White,
                modifier = Modifier
                    .fillMaxWidth(0.6F)
            )
        }
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Malac Prodavac",
            tint = MP_White,
            modifier = Modifier
                .size(100.dp)
        )
    }
}

@Composable
fun ShowcaseProducts(
    products: List<Product>?,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = 7.5.dp,
            start = 7.5.dp,
            end = 7.5.dp,
            bottom = 80.dp
        ), // 100 dp bottom padding because navigation
        modifier = Modifier.fillMaxHeight(),
    ) {
        if (products != null) {
            items(products.size) {// how many items do we have
                // define one of items
                StoreCategoryProduct(product = products?.get(it) ?: null, navController)
            }
        }
    }
}

@Composable
fun StoreCategoryProduct (
    product: Product?,
    navController: NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 7.5.dp, end = 7.5.dp, bottom = 15.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
            )
            .padding(1.5.dp)
            .aspectRatio(0.8F) // ratio is 1x1 so whatever the width is, the hegiht will be the same
            .clip(RoundedCornerShape(10.dp))
            .background(MP_White)
            .clickable {
                navController.navigate(Screen.ProductScreen.route)
            }
    ) {
        if (product != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Box(
                        modifier = Modifier
                            .size(
                                width = 150.dp,
                                height = 125.dp
                            )
                            .background(MP_Gray)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "home icon",
                            tint = MP_White,
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(
                                top = 7.5.dp
                            )
                    ) {
                        Text(
                            text = if (product.title.length <= 16 ){
                                product.title
                            }else{
                                product.title.take(16) + "..."
                            },
                            style = MaterialTheme.typography.body2,
                            color = MP_Black
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 5.dp
                                )
                        ) {
                            Text(
                                text = product.price.toString() + " rsd",
                                style = MaterialTheme.typography.body2,
                                color = MP_Green,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = if (product.available) {
                                    Icons.Filled.Favorite
                                } else {
                                    Icons.Filled.FavoriteBorder
                                },
                                contentDescription = "favourite",
                                tint = MP_Pink,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}