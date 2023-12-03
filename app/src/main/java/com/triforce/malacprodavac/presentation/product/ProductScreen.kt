package com.triforce.malacprodavac.presentation.product

import android.annotation.SuppressLint
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
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteEvent
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.components.ProductAmount
import com.triforce.malacprodavac.presentation.components.RatingStars
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.components.ShowHighlightSectionComp
import com.triforce.malacprodavac.presentation.product.components.CreateReviewDialog
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController, viewModel: ProductViewModel = hiltViewModel()
) {
    var isCreateReviewOpen by remember { mutableStateOf(false) }
    val openCreateReviewDialog = {
        isCreateReviewOpen = true
    }
    val closeCreateReviewDialog = {
        isCreateReviewOpen = false
    }

    val createReviewCallback = { text: String, rating: Int ->
        viewModel.onEvent(ProductEvent.CreateReview(text, rating))
    }

    val viewModelFavProduct: FavoriteViewModel = hiltViewModel()

    val state = viewModel.state

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
            .height(1300.dp)
    ) {

        if (isCreateReviewOpen) {
            CreateReviewDialog(closeCreateReviewDialog, createReviewCallback)
        }

        LinearGradient(color1 = colorForeground, color2 = colorBackground)
        RoundedBackgroundComp(top = 250.dp, color = MP_White)

        Column {
            GoBackComp("Malac Pijaca", navController)
            ProductHeroImage()

            if (product != null) {
                Spacer(modifier = Modifier.padding(16.dp))
                ProductDetails(product = product)

                Spacer(modifier = Modifier.padding(16.dp))
                ProductOptions(product, navController, true)

                Spacer(modifier = Modifier.padding(16.dp))
                ShowFavouriteAddToCart(
                    navController = navController,
                    viewModel = viewModel,
                    viewModelFavourite = viewModelFavProduct
                )

                Spacer(Modifier.height(16.dp))
                ShowHighlightSectionComp(
                    navController = navController,
                    products = listOf(
                        Product(
                            1,
                            2,
                            3,
                            true,
                            99.0,
                            "RSD",
                            9.0,
                            null,
                            null,
                            null,
                            null,
                            "RSD",
                            "Prsuta 100g",
                            "",
                            null,
                            null,
                            "",
                            "",
                            null,
                            emptyList(),
                            null,
                            null,
                            emptyList(),
                            null
                        ), Product(
                            1,
                            2,
                            3,
                            true,
                            99.0,
                            "RSD",
                            9.0,
                            null,
                            null,
                            null,
                            null,
                            "RSD",
                            "Prsuta 100g",
                            "",
                            null,
                            null,
                            "",
                            "",
                            null,
                            emptyList(),
                            null,
                            null,
                            emptyList(),
                            null
                        )
                    ),
                    title = "Više proizvoda od prodavca",
                    route = Screen.HighlightSection.route
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Vaši Komentari",
                        style = MaterialTheme.typography.body1,
                        color = MP_Black,
                        fontWeight = FontWeight.W500
                    )
                    IconButton(onClick = openCreateReviewDialog) {
                        Icon(Icons.Filled.Add, contentDescription = "Create a Review")
                    }
                }

                Spacer(Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    state.reviews?.forEach {
                        Text(
                            text = it.text.ifEmpty { "Korisnik nije ostavio komentar" },
                            softWrap = true,
                        )
                        RatingStars(
                            rating = it.rating.toDouble()
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                it.createdAt.split("T")[0]
                            )
                            Text(
                                it.customer?.user?.firstName + " " + it.customer?.user?.lastName
                            )
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun ProductOptions(
    product: Product?, navController: NavController, isEdit: Boolean
) {
    val colorTint = if (isEdit) {
        MP_Orange_Dark
    } else {
        MP_Green
    }
    val msg = if (isEdit) {
        "Izmeni proizvod"
    } else {
        "Dodaj novi proizvod"
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                start = 20.dp, end = 20.dp
            )
            .shadow(
                elevation = 10.dp, spotColor = MP_Black, shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MP_White)
            .padding(
                vertical = 10.dp, horizontal = 16.dp
            )
    ) {
        Icon(
            imageVector = if (isEdit) {
                Icons.Outlined.Edit
            } else {
                Icons.Outlined.Add
            },
            contentDescription = "FavoriteBorder",
            tint = colorTint,
            modifier = Modifier.size(35.dp)
        )
        Text(text = msg,
            style = MaterialTheme.typography.body2,
            color = MP_White,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable {
                    if (product != null) {
                        navController.navigate(Screen.AddEditProduct.route + "?productId=${product.id}")
                    } else {
                        navController.navigate(Screen.AddEditProduct.route)
                    }
                }
                .clip(RoundedCornerShape(15.dp))
                .background(colorTint)
                .width(width = 200.dp)
                .padding(vertical = 10.dp))
    }
}

@Composable
fun ProductHeroImage(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .background(MP_White, RoundedCornerShape(10.dp))
            .padding(35.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Malac Prodavac",
            tint = MP_Gray,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun ProductDetails(
    product: Product,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp, end = 20.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 20.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.h5,
                    color = MP_Black,
                    fontWeight = FontWeight.W500
                )
                RatingStars(rating = product.rating!!)
            }


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
    navController: NavController, viewModel: ProductViewModel, viewModelFavourite: FavoriteViewModel
) {

    val imageVector: ImageVector

    if (viewModel.state.isFavorite == true) imageVector = Icons.Outlined.Favorite
    else imageVector = Icons.Outlined.FavoriteBorder

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
                bottom = 25.dp, start = 5.dp, end = 5.dp
            )
            .shadow(
                elevation = 10.dp, spotColor = MP_Black, shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MP_White)
            .padding(
                vertical = 10.dp,
            )
    ) {
        Icon(imageVector = imageVector,
            contentDescription = "FavoriteBorder",
            tint = MP_Pink,
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    if (viewModel.state.isFavorite == false) {
                        viewModel.onEvent(ProductEvent.favoriteProduct)
                        viewModelFavourite.onEvent(FavoriteEvent.AddFavProduct)

                        Toast
                            .makeText(
                                context,
                                "Uspešno dodat proizvod u listu omiljenih proizvoda",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    } else {

                        viewModel.onEvent(ProductEvent.removeFavoriteProduct)
                        viewModelFavourite.onEvent(FavoriteEvent.DeleteFavProduct)
                        Toast
                            .makeText(
                                context,
                                "Proizvod se već nalazi u listi omiljenih proizvoda",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                })

        Text(text = "Dodaj u korpu",
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
                                context, "Uspešno dodat proizvod u korpu", Toast.LENGTH_LONG
                            )
                            .show()
                    } else {
                        Toast
                            .makeText(
                                context, "Proizvod se već nalazi u korpi", Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }
                .clip(RoundedCornerShape(20.dp))
                .background(MP_Pink)
                .width(width = 250.dp)
                .padding(vertical = 10.dp))
    }
}