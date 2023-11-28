package com.triforce.malacprodavac.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.triforce.malacprodavac.presentation.FavProducts.FavoriteViewModel
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopEvent
import com.triforce.malacprodavac.presentation.FavShops.FavoriteShopViewModel
import com.triforce.malacprodavac.presentation.profile.profilePublic.ProfilePublicEvent
import com.triforce.malacprodavac.presentation.profile.profilePublic.ProfilePublicViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun CallToActionFavourite (
    content: String,
    viewModel: ProfilePublicViewModel,
    viewModelFavShop: FavoriteShopViewModel
) {
    val context = LocalContext.current

    val viewModelFavShop: FavoriteShopViewModel = hiltViewModel()
    val state = viewModel.state

    val imageVector: ImageVector

    if(viewModel.state.isFavorite == true) imageVector = Icons.Outlined.Favorite
    else imageVector = Icons.Outlined.FavoriteBorder

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MP_Pink)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.subtitle1,
            color = MP_White,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .width(210.dp)
        )
        Icon(
            imageVector = imageVector,
            contentDescription = "Omiljen",
            tint = MP_White,
            modifier = Modifier
                .size(100.dp)
                .clickable {
                    if (viewModel.state.isFavorite == false) {
                        viewModel.onEvent(ProfilePublicEvent.favoriteShop)
                        viewModelFavShop.onEvent(FavoriteShopEvent.AddFavShop)

                        Toast
                            .makeText(
                                context,
                                "Uspešno dodat proizvođač u listu omiljenih proizvođača",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    } else {
                        viewModel.onEvent(ProfilePublicEvent.removeFavoriteShop)
                        viewModelFavShop.onEvent(FavoriteShopEvent.DeleteFavShop)
                        Toast
                            .makeText(
                                context,
                                "Proizvođač se već nalazi u listi omiljenih proizvođača",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }
        )
    }
}