package com.triforce.malacprodavac.presentation.profiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.profiles.components.ShowData
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun ProfileCustomerScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){

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

            Text(
                text = "Profil",
                style = MaterialTheme.typography.h5,
                lineHeight = 15.sp,
                color = MP_White
            )

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "ProfilePicture",
                tint = MP_White,
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = "Uroš Petronijević",
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
            Text(
                text = "Odjavi se",
                style = MaterialTheme.typography.h6,
                lineHeight = 15.sp,
                color = Color.Red
            )

            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "logout",
                tint = Color.Red,
                modifier = Modifier
                    .size(40.dp)
            )

        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(top = 200.dp)
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ShowData(
                title = "Email",
                data = "test@gmail.com",
                contentDescription = "email",
                icon = Icons.Default.Email
            )

            ShowData(
                title = "Adresa",
                data = "Gavrila Principa, Kragujevac",
                contentDescription = "location",
                icon = Icons.Default.LocationOn
            )

            ShowData(
                title = "Kontakt telefon",
                data = "+381 61726814",
                contentDescription = "phoneNumber",
                icon = Icons.Default.Phone
            )

            ShowData(
                title = "Način plaćanja",
                data = "Paypal",
                contentDescription = "payment method",
                icon = Icons.Default.ShoppingCart
            )
        }

        BottomNavigationMenu(
            navController = navController,
            items = listOf(
                BottomNavigationMenuContent(
                    title = "Početna",
                    graphicID = Icons.Default.Home,
                    screen = Screen.HomeScreen,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Prodavnica",
                    graphicID = Icons.Default.AddCircle,
                    screen = Screen.StoreScreen,
                    isActive = true
                ),
                BottomNavigationMenuContent(
                    title = "Moj Profil",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.ProfileCustomer,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Korpa",
                    graphicID = Icons.Default.ShoppingCart,
                    screen = Screen.CartScreen,
                    isActive = false
                )
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}