package com.triforce.malacprodavac.presentation.product.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.R
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.products.Product
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteEvent
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.triforce.malacprodavac.presentation.cart.components.ProductAmount
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.RatingStars
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.components.ShowHighlightSectionComp
import com.triforce.malacprodavac.presentation.product.ProductEvent
import com.triforce.malacprodavac.presentation.product.ProductViewModel
import com.triforce.malacprodavac.presentation.product.components.CreateReviewDialog
import com.triforce.malacprodavac.presentation.product.components.ProductDetails
import com.triforce.malacprodavac.presentation.product.components.ProductHeroImage
import com.triforce.malacprodavac.presentation.product.components.ProductScreenContent
import com.triforce.malacprodavac.presentation.product.components.ShowFavouriteAddToCart
import com.triforce.malacprodavac.presentation.profile.profilePrivate.components.ProductOptions
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_Pink_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProductScreenContent(
    navController: NavController,
    viewModel: ProductViewModel,
) {
    var isCreateReviewOpen by remember { mutableStateOf(false) }

    val openCreateReviewDialog = { isCreateReviewOpen = true }
    val closeCreateReviewDialog = { isCreateReviewOpen = false }

    val createReviewCallback = { text: String, rating: Int ->
        viewModel.onEvent(ProductEvent.CreateReview(text, rating))
    }

    val state = viewModel.state
    val product = state.product
    val shop = state.shop

    var colorForeground = MP_Green
    var colorBackground = MP_GreenLight

    val scrollState = rememberScrollState()

    if (product != null && shop != null) {
        Scaffold(
            content = { padding ->
                Box(
                    modifier = Modifier
                        .verticalScroll(state = scrollState)
                        .background(MP_White)
                        .padding(padding)
                        .height(1300.dp)
                ) {

                    if (isCreateReviewOpen) {
                        CreateReviewDialog(closeCreateReviewDialog, createReviewCallback)
                    }

                    LinearGradient(color1 = colorForeground, color2 = colorBackground)
                    RoundedBackgroundComp(top = 260.dp, color = MP_White)

                    Column {
                        Spacer(modifier = Modifier.padding(16.dp))
                        ProductHeroImage(imageUrl = state.thumbnailUrl)

                        Spacer(modifier = Modifier.padding(22.dp))
                        ProductDetails(product = product)

                        if (product.shopId == viewModel.state.user?.shop?.id) {
                            Spacer(modifier = Modifier.padding(16.dp))
                            ProductOptions(product, navController, true)
                        }

                        Spacer(Modifier.height(22.dp))
                        ShowHighlightSectionComp(
                            navController = navController,
                            products = shop.products,
                            title = "Više od ${shop.businessName}",
                            route = Screen.HighlightSection.route + "?id=${shop.id}"
                        )

                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Komentari:",
                                style = MaterialTheme.typography.body2,
                                color = MP_Black,
                                fontWeight = FontWeight.W500
                            )
                            IconButton(onClick = openCreateReviewDialog) {
                                Icon(Icons.Filled.Add, contentDescription = "Create a Review")
                            }
                        }

                        if (state.reviews != null) {

                            Spacer(Modifier.height(16.dp))
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .weight(1f)
                            ) {
                                items(state.reviews) { review ->
                                    Column {
                                        Text(
                                            text = review.text.ifEmpty { "Korisnik nije ostavio komentar" },
                                            softWrap = true,
                                        )
                                        RatingStars(
                                            rating = review.rating.toDouble()
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                review.createdAt.split("T")[0]
                                            )
                                            Text(
                                                review.customer?.user?.firstName + " " + review.customer?.user?.lastName
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}