package com.triforce.malacprodavac.presentation.profile

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.EditState
import com.triforce.malacprodavac.presentation.login.LoginFormEvent
import com.triforce.malacprodavac.presentation.profile.components.ShowData
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    val state = viewModel.state

    if (!viewModel.isLoggedIn())
        LaunchedEffect(Unit) {
            navController.navigate(Screen.LoginScreen.route)
        }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .background(MP_Green),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(20.dp))

            var imageUri by remember { mutableStateOf<Uri?>(null) }
            val context = LocalContext.current
            val bitmap = remember { mutableStateOf<Bitmap?>(null) }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()) {uri: Uri? ->
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
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(MP_Black)
                            .padding(4.dp) // optional: add padding for visual effect
                            .border(2.dp, Color.White, CircleShape)
                    ){
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clickable { launcher.launch("image/*") }
                                .clip(RoundedCornerShape(size = 200.dp))
                        )
                    }
                }
            }


            if(state.newImage == false){
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "accountImage",
                    tint = MP_Gray,
                    modifier = Modifier
                        .size(120.dp)
                        .clickable { launcher.launch("image/*");
                            viewModel.onEvent(ProfileEvent.onAddMediaButtonPress)
                        }

                )
            }

//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data("http://softeng.pmf.kg.ac.rs:10010/users/3/medias/2")
//                    .build(),
//                modifier = Modifier
//                    .size(100.dp)
//                    .clickable { launcher.launch("image/*") },
//                contentDescription = "Profilna Slika"
//            )
            Text(
                text = "${state.currentUser?.firstName}  ${state.currentUser?.lastName}",
                style = MaterialTheme.typography.h4,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MP_White
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { viewModel.onEvent(ProfileEvent.Logout) },
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "logout",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(40.dp)
                )
            }


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(top = 200.dp)
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(state.email == null)
            {
                ShowData(
                    title = "Email",
                    data = state.currentUser?.email ?: "",
                    contentDescription = "email",
                    icon = Icons.Default.Email
                )
            }
            else {
                ShowData(
                    title = "Email",
                    data = state.email,
                    contentDescription = "email",
                    icon = Icons.Default.Email
                )
            }

            ShowData(
                title = "Adresa",
                data = state.currentUser?.address ?: "",
                contentDescription = "location",
                icon = Icons.Default.LocationOn
            )

            ShowData(
                title = "Kontakt telefon",
                data = state.currentUser?.phoneNumber ?: "",
                contentDescription = "phoneNumber",
                icon = Icons.Default.Phone
            )

            ShowData(
                title = "Naziv preduzeća",
                data = state.currentUser?.address ?: "",
                contentDescription = "company",
                icon = Icons.Default.Info
            )

            EditState(viewModel = viewModel)
        }

        BottomNavigationMenu(
            navController = navController,
            items = listOf(
                BottomNavigationMenuContent(
                    title = "Početna",
                    graphicID = Icons.Default.Home,
                    screen = Screen.HomeScreen,
                    isActive = true
                ),
                BottomNavigationMenuContent(
                    title = "Prodavnica",
                    graphicID = Icons.Default.AddCircle,
                    screen = Screen.StoreScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Moj Profil",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.Profile,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Korpa",
                    graphicID = Icons.Default.ShoppingCart,
                    screen = Screen.CartScreen,
                    isActive = false
                )
            ), modifier = Modifier
                .align(Alignment.BottomCenter)
        )
//        EditStatePopupDemo(viewModel)

    }
}