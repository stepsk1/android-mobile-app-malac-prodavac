package com.triforce.malacprodavac.presentation.store

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.presentation.home.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.home.GreetingSection
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun StoreScreen(navController: NavController)
{
    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ){
        LinearGradient(color1 = MP_GreenLight, color2 = MP_GreenDark )
        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
                .padding(top = 67.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(color = MP_Pink)
        ){

        }
        Column {
            HeaderSectionTitle("Malac prodavnica", navController)
            TitleTextContentSection(
                sectionTitle = "Podržite lokalnu ekonomiju",
                sectionText = "Pronađite najbolje proizvode od malih proizvođača. Pratite svoje omiljene proizvođače i podržite lokalnu ekonomiju!",
                iconImage = Icons.Default.Star,
                iconColor = MP_Green
            )
        }

        BottomNavigationMenu(
            items = listOf(
                BottomNavigationMenuContent(
                    title = "Početna",
                    graphicID = Icons.Default.Home
                ),
                BottomNavigationMenuContent(
                    title = "Prodavnica",
                    graphicID = Icons.Default.AddCircle
                ),
                BottomNavigationMenuContent(
                    title = "Moj Profil",
                    graphicID = Icons.Default.AccountCircle
                ),
                BottomNavigationMenuContent(
                    title = "Korpa",
                    graphicID = Icons.Default.ShoppingCart
                )
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun HeaderSectionTitle(
    msg: String,
    navController: NavController,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(width = 240.dp, height = 35.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Search",
                tint = MP_White,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate(Screen.HomeScreen.route)
                    }
            )

            Text(
                text = msg,
                style = MaterialTheme.typography.h5,
                color = MP_White
            )
        }

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MP_White,
            modifier = Modifier
                .size(35.dp)
        )
    }
}

@Composable
fun TitleTextContentSection(
    sectionTitle: String,
    sectionText: String,
    iconImage: ImageVector,
    iconColor: Color
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = sectionTitle,
                style = MaterialTheme.typography.h6,
                color = MP_Black
            )

            Icon(
                imageVector = iconImage,
                contentDescription = iconImage.toString(),
                tint = iconColor,
                modifier = Modifier
                    .size(35.dp)
            )
        }

        Text(
            text = sectionText,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 15.dp)
        )
    }
}