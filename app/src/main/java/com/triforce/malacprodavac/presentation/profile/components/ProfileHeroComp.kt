package com.triforce.malacprodavac.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProfileHeroComp(
    user: User?,
    navController: NavController
) {
    if (user != null) {
        Column(
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(
                        Brush.linearGradient(

                            0.0f to MP_GreenLight,
                            500.0f to MP_GreenDark,

                            start = Offset.Zero,
                            end = Offset.Infinite
                        )
                    )
            ) {
                GoBackComp(msg = "Profil", navController = navController)

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 30.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .height(160.dp)
                    ) {

                        Column {
                            Text(
                                text = "Malac ${user.roles.first()}",
                                style = MaterialTheme.typography.body1,
                                color = MP_White,
                                fontWeight = FontWeight.W500
                            )
                            Text(
                                text = "${user.firstName} ${user.lastName[0]}.",
                                style = MaterialTheme.typography.h4,
                                color = MP_White,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Column {
                            Text(
                                text = "Jagodina, Srbija",
                                style = MaterialTheme.typography.body2,
                                color = MP_White,
                                fontWeight = FontWeight.W500,
                            )
                            Text(
                                text = "Ocena 9.8",
                                style = MaterialTheme.typography.body2,
                                color = MP_White,
                                fontWeight = FontWeight.W500
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .width(
                                    if ( user.roles.first().equals("Shop", ignoreCase = true)) 120.dp
                                    else 75.dp
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = "Izmeni",
                                tint = MP_White,
                                modifier = Modifier
                                    .size(35.dp)
                            )
                            Icon(
                                imageVector = Icons.Rounded.Info,
                                contentDescription = "Izmeni",
                                tint = MP_White,
                                modifier = Modifier
                                    .size(35.dp)
                            )

                            if ( user.roles.first().equals("Shop", ignoreCase = true))
                            {
                                Icon(
                                    imageVector = Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Omiljen",
                                    tint = MP_White,
                                    modifier = Modifier
                                        .size(35.dp)
                                )
                            }
                        }

                    }

                    Image(
                        painter = painterResource(androidx.customview.R.drawable.notify_panel_notification_icon_bg),
                        contentDescription = "Round Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(175.dp)
                            .clip(CircleShape)
                            .border(3.dp, MP_White, CircleShape)
                    )
                }
            }
        }
    }
}