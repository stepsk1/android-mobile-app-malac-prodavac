package com.triforce.malacprodavac.presentation.profile.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.domain.model.User
import com.triforce.malacprodavac.presentation.profile.PhotoUrl
import com.triforce.malacprodavac.presentation.profile.profilePrivate.ProfilePrivateEvent
import com.triforce.malacprodavac.presentation.profile.profilePrivate.ProfilePrivateViewModel
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProfilePrivateHeroComp(
    user: User?,
    navController: NavController,
    viewModel: ProfilePrivateViewModel = hiltViewModel(),
    private: Boolean
) {
    val state = viewModel.state

    if (user != null) {
        Column(
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(
                        if (!private) {
                            Brush.linearGradient(

                                0.0f to MP_GreenDark,
                                500.0f to MP_GreenLight,

                                start = Offset.Zero,
                                end = Offset.Infinite
                            )
                        } else {
                            Brush.linearGradient(

                                0.0f to MP_Orange_Dark,
                                500.0f to MP_Orange,

                                start = Offset.Zero,
                                end = Offset.Infinite
                            )
                        }
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
                                    if ( user.roles.first().equals("Shop", ignoreCase = true) && private ) 120.dp
                                    else 75.dp
                                )
                        ) {
                            if ( private ) {

                                Icon(
                                    imageVector = Icons.Rounded.AccountCircle,
                                    contentDescription = "Izmeni",
                                    tint = MP_White,
                                    modifier = Modifier
                                        .size(35.dp)
                                        .clickable { }
                                )

                                if (user.roles.first().equals("Shop", ignoreCase = true)) {
                                    Icon(
                                        imageVector = Icons.Rounded.AddCircle,
                                        contentDescription = "Dodaj",
                                        tint = MP_White,
                                        modifier = Modifier
                                            .size(35.dp)
                                            .clickable { }
                                    )
                                }
                            }else {
                                if (user.roles.first().equals("Shop", ignoreCase = true)) {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Omiljen",
                                        tint = MP_White,
                                        modifier = Modifier
                                            .size(35.dp)
                                            .clickable { }
                                    )
                                }
                            }

                            Icon(
                                imageVector = Icons.Rounded.Email,
                                contentDescription = "Poruka",
                                tint = MP_White,
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {  }
                            )
                        }

                    }

                    var imageUri by remember { mutableStateOf<Uri?>(null) }
                    val context = LocalContext.current
                    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
                        imageUri = uri
                    }

                    imageUri?.let {
                        if(Build.VERSION.SDK_INT < 28){
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(context.contentResolver, it)
                        }else {
                            val source = ImageDecoder.createSource(context.contentResolver, it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let { btm ->

                                PhotoUrl.photoBitMap = btm.asImageBitmap()
                        }
                    }

                    if (private == true && PhotoUrl.changed == true){

                        Box(
                            modifier = Modifier.clickable { launcher.launch("image/*") }
                        ){
                            Image(
                                bitmap = PhotoUrl.photoBitMap,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(175.dp)
                                    .clip(CircleShape)
                                    .background(MP_Black)
                                    .border(3.dp, MP_White, CircleShape)
                            )
                        }
                    }

                    if (private == true && PhotoUrl.changed == false)
                    {
                        Image(
                            painter = painterResource(androidx.customview.R.drawable.notify_panel_notification_icon_bg),
                            contentDescription = "Round Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(175.dp)
                                .clip(CircleShape)
                                .border(3.dp, MP_White, CircleShape)
                                .clickable {
                                    launcher.launch("image/*")
                                    PhotoUrl.changed = true
                                    viewModel.onEvent(ProfilePrivateEvent.onAddMediaButtonPress)
                                }
                        )
                    }
                }
            }
        }
    }
}