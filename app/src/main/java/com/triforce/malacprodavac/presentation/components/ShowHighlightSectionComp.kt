package com.triforce.malacprodavac.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.presentation.category.StoreCategoryProduct
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ShowHighlightSectionComp(
    navController: NavController,
    products: List<Product>?,
    title: String,
    route: String
) {
    val subProducts = if ( products != null ) {
        products.subList(0, if (products.size > 3) { 3 } else { products.size })
    } else null

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1,
                color = MP_Black,
                fontWeight = FontWeight.W500
            )
            Log.d("NavController222", route)
            Text(
                text = "Vidi više",
                style = MaterialTheme.typography.caption,
                color = MP_White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate(route)
                    }
                    .clip(RoundedCornerShape(15.dp))
                    .background(MP_Pink)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }

        ShowHighlightedProducts(subProducts, navController)
    }
}

@Composable
fun ShowHighlightedProducts(
    products: List<Product>?,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = 7.5.dp,
            start = 7.5.dp,
            end = 7.5.dp
        )
    ) {
        if (products != null) {
            items(products.size) {// how many items do we have
                // define one of items
                HighlightSectionProduct(product = products.get(it) ?: null, navController)
            }
        }
    }
}

@Composable
fun HighlightSectionProduct (
    product: Product?,
    navController: NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 7.5.dp, end = 7.5.dp, top = 7.5.dp, bottom = 15.dp)
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
                if (product != null) {
                    navController.navigate(Screen.ProductScreen.route + "?productId=${product.id}")
                }
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

                    }
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(
                                top = 7.5.dp
                            )
                    ) {
                        androidx.compose.material3.Text(
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
                            androidx.compose.material3.Text(
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