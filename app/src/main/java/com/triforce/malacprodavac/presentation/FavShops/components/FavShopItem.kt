package com.triforce.malacprodavac.presentation.FavShops.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.customers.FavoriteShop
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopEvent
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_Pink_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun FavShopItem(
    favoriteShop: FavoriteShop,
    viewModel: FavoriteShopViewModel,
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }

    BoxWithConstraints(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(MP_Gray)
            .requiredHeight(160.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .clickable {
                    navController.navigate(Screen.PublicProfile.route + "?id=${favoriteShop.shopId}&role=1")
                }
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround,
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = favoriteShop.shop!!.businessName!!,
                        style = MaterialTheme.typography.h6,
                        color = MP_Black
                    )

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Delete one",
                        tint = MP_Pink,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable {
                                showDialog = true
                            }
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        containerColor = MP_White,
                        onDismissRequest = {
                            showDialog = false
                        },
                        title = {
                            Text(
                                text = "Obriši prodavca iz liste omiljenih",
                                style = MaterialTheme.typography.h5,
                                color = MP_Pink_Dark,
                                fontWeight = FontWeight.W300
                            )
                        },
                        text = {
                            Text(
                                text = "Da li ste sigurni da želite da obrišete ${favoriteShop.shop!!.businessName} iz liste omiljenih prodavca?",
                                style = MaterialTheme.typography.body1,
                                color = MP_Black,
                                fontWeight = FontWeight.W300
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    viewModel.onEvent(FavoriteShopEvent.DeleteFavShop(favoriteShop.id))
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MP_Green
                                )
                            ) {
                                Text(
                                    text = "Da",
                                    style = MaterialTheme.typography.body1,
                                    color = MP_White
                                )
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    showDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MP_Pink
                                )
                            ) {
                                Text(
                                    text = "Ne",
                                    style = MaterialTheme.typography.body1,
                                    color = MP_White
                                )
                            }
                        }
                    )
                }

                Text(
                    text = "Od " + favoriteShop.shop!!.openFromDays + " do " + favoriteShop.shop.openTillDays,
                    style = MaterialTheme.typography.body2,
                    color = MP_Black
                )
                Text(
                    text = "Adresa: " + favoriteShop.shop.user?.address,
                    style = MaterialTheme.typography.body2,
                    color = MP_Black
                )
                Text(
                    text = "Email: " + favoriteShop.shop.user?.email,
                    style = MaterialTheme.typography.body2,
                    color = MP_Black
                )
                Text(
                    text = "Telefon: " + favoriteShop.shop.user?.phoneNumber,
                    style = MaterialTheme.typography.body2,
                    color = MP_Black
                )
            }
        }
    }
}