package com.triforce.malacprodavac.presentation.store

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.Screen
import com.triforce.malacprodavac.domain.model.Category
import com.triforce.malacprodavac.presentation.components.BottomNavigationMenu
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun StoreScreen(navController: NavController)
{
    val viewModel: StoreViewModel = hiltViewModel()
    val state = viewModel.state
    val context = LocalContext.current

    val Categories: List<Category> = state.categories
    var features : List<Feature> = listOf()
    var flagNumber: Int = 0

    Categories.forEach {
        if (it.parentCategoryId == null) {
            flagNumber++
            if (it.id % 3 == 1) {
                features += Feature(
                    id = it.id,
                    title = it.name,
                    graphicID = Icons.Default.Star,
                    color1 = MP_Green,
                    color2 = MP_Green,
                    screen = Screen.StoreScreen
                )
            } else if (it.id % 3 == 2) {
                features += Feature(
                    id = it.id,
                    title = it.name,
                    graphicID = Icons.Default.Star,
                    color1 = MP_Pink,
                    color2 = MP_Pink,
                    screen = Screen.StoreScreen
                )
            } else {
                features += Feature(
                    id = it.id,
                    title = it.name,
                    graphicID = Icons.Default.Star,
                    color1 = MP_Orange,
                    color2 = MP_Orange,
                    screen = Screen.StoreScreen
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ){
        LinearGradient(color1 = MP_GreenDark, color2 = MP_GreenLight)

        RoundedBackgroundComp(top = 65.dp, color = MP_White)

        Column {
            GoBackComp("Malac prodavnica", navController)
            TitleTextContentSection(
                sectionTitle = "Podržite lokalnu ekonomiju",
                sectionText = "Pronađite najbolje proizvode od malih proizvođača. Pratite svoje omiljene proizvođače i podržite lokalnu ekonomiju!",
                iconImage = Icons.Default.Star,
                iconColor = MP_Green
            )
            StoreCategoriesSection(
                features = features,
                navController
            )
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
                    title = "Public",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.PublicProfile,
                    isActive = false
                ),
                BottomNavigationMenuContent(
                    title = "Private",
                    graphicID = Icons.Default.AccountCircle,
                    screen = Screen.PrivateProfile,
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

@Composable
fun StoreCategoriesSection(
    features: List<Feature>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            start = 7.5.dp,
            end = 7.5.dp,
            bottom = 80.dp
        ), // 100 dp bottom padding because navigation
        modifier = Modifier.fillMaxHeight(),
    ) {
        items(features.size) {// how many items do we have
            // define one of items
            StoreCategoryItem(feature = features[it], navController)
        }
    }
}

@Composable
fun StoreCategoryItem(
    feature: Feature,
    navController: NavController
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(start = 7.5.dp, end = 7.5.dp, bottom = 15.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
            )
            .clickable {
                navController.navigate(Screen.StoreCategoryScreen.route + "?categoryId=${feature.id}&title=${feature.title}")
            }
            .padding(1.5.dp)
            .aspectRatio(1F) // ratio is 1x1 so whatever the width is, the hegiht will be the same
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.linearGradient(
                    0.0f to feature.color1,
                    0.5f to feature.color2,

                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.body1,
                color = MP_White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
            Icon(
                imageVector = feature.graphicID,
                contentDescription = feature.title,
                tint = MP_White,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(100.dp)
            )
        }
    }
}