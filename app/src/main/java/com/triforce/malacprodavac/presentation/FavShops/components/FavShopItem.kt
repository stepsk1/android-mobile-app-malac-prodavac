package com.triforce.malacprodavac.presentation.FavShops.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.customers.FavoriteProduct
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteEvent
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopEvent
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink

@Composable
fun FavShopItem(
    favoriteShop: FavoriteShop,
    viewModel: FavoriteShopViewModel,
    navController: NavController
) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(3.5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
            .background(MP_Gray)
            .requiredHeight(160.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .clickable {
                    navController.navigate(Screen.PublicProfile.route + "?id=${favoriteShop.shopId}&role=1")
                }
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = favoriteShop.shop!!.businessName!!, //.product!!.title,
                    style = MaterialTheme.typography.h6,
                    color = MP_Black
                )

                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete one",
                    tint = MP_Pink,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            viewModel.onEvent(FavoriteShopEvent.DeleteFavShop)
                        }
                )
            }

            Text(
                text = favoriteShop.shop!!.openFromDays + favoriteShop.shop!!.openTillDays,
                style = MaterialTheme.typography.body2,
                color = MP_Green,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )

//            Text(
//                text = favoriteProduct.product!!.price.toString() + " " +favoriteProduct.product!!.currency,
//                style = MaterialTheme.typography.h6,
//                color = MP_Black,
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//            )
        }
    }
}

