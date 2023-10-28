package com.triforce.malacprodavac.presentation.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import com.triforce.malacprodavac.Screen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.presentation.RegistrationFormEvent
import com.triforce.malacprodavac.presentation.login.LoginFormEvent
import com.triforce.malacprodavac.presentation.login.LoginViewModel
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
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_GreenLight, color2 = MP_GreenDark)
        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 100.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
        ){

        }
        Column {
            GreetingSection(msg = "Početna strana", subMsg = "Pretražite Malac Prodavac")
            CategoriesSection(categories = listOf("Pijaca", "Profil", "Porudžbine", "Korpa", "Prodavnica"))
            GoToStoreSection(navController)
            RecommendedFeaturesSection(
                features = listOf(
                    Feature(
                        title = "Prodavnica",
                        graphicID = Icons.Default.AddCircle,
                        color1 = MP_Green,
                        color2 = MP_GreenLight
                    ),
                    Feature(
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Orange_Dark,
                        color2 = MP_Orange
                    ),
                    Feature(
                        title = "Omiljeno",
                        graphicID = Icons.Default.Favorite,
                        color1 = MP_Orange_Dark,
                        color2 = MP_Orange
                    ),
                    Feature(
                        title = "Korpa",
                        graphicID = Icons.Default.ShoppingCart,
                        color1 = MP_Green,
                        color2 = MP_GreenLight
                    ),
                    Feature(
                        title = "Prodavnica",
                        graphicID = Icons.Default.AddCircle,
                        color1 = MP_Green,
                        color2 = MP_GreenLight
                    ),
                    Feature(
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        color1 = MP_Orange,
                        color2 = MP_Orange_Dark
                    )
                )
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
            ), modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun GreetingSection(
    name: String = "Filip",
    msg: String,
    subMsg: String = ""
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = msg,
                style = MaterialTheme.typography.h4,
                color = MP_White
            )
            Text(
                text = subMsg,
                style = MaterialTheme.typography.body1,
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
fun CategoriesSection(
    categories: List<String>
) {
    var selectedCategoryIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(categories.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 30.dp, bottom = 5.dp)
                    .shadow(
                        elevation = 5.dp,
                        spotColor = MP_Black,
                        shape = RoundedCornerShape(7.5.dp)
                    )
                    .clickable {
                        selectedCategoryIndex = it // current index of the box
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedCategoryIndex == it)
                            Brush.linearGradient(
                                0.0f to MP_Pink_Dark,
                                500.0f to MP_Pink,

                                start = Offset.Zero,
                                end = Offset.Infinite
                            )
                        else
                            Brush.linearGradient(
                                0.0f to MP_GreenLight,
                                0.5f to MP_Green,

                                start = Offset.Zero,
                                end = Offset.Infinite
                            )

                    )
                    .padding(15.dp)
            )
            {
                Text(text = categories[it], color = MP_White)
            }
        }
    }
}

@Composable
fun GoToStoreSection(
    navController: NavController,
    color1: Color = MP_Pink,
    color2: Color = MP_Pink_Dark
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(14.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
            )
            .padding(1.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.linearGradient(
                    0.0f to color1,
                    500.0f to color2,

                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .clickable {
                navController.navigate(Screen.HomeScreen.route)
            }
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Otvori prodavnicu",
                style = MaterialTheme.typography.h5,
                color = MP_White
            )
            Text(
                text = "Od sirupa do sira!",
                style = MaterialTheme.typography.body1,
                color = MP_White
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MP_White)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Store",
                tint = MP_Pink,
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}

@Composable
fun RecommendedFeaturesSection(
    features: List<Feature>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Podržite lokalnu ekonomiju",
            style = MaterialTheme.typography.h5,
            color = MP_Black,
            modifier = Modifier
                .padding(start = 15.dp, bottom = 15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 7.5.dp,
                end = 7.5.dp,
                bottom = 100.dp
            ), // 100 dp bottom padding because navigation
            modifier = Modifier.fillMaxHeight(),
        ) {
            items(features.size) {// how many items do we have
                // define one of items
                RecommendedFeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun RecommendedFeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(6.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
                )
            .padding(1.5.dp)
            .aspectRatio(1.5F) // ratio is 1x1 so whatever the width is, the hegiht will be the same
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
                style = MaterialTheme.typography.h6,
                lineHeight = 26.sp,
                color = MP_White,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
            Icon(
                imageVector = feature.graphicID,
                contentDescription = feature.title,
                tint = MP_White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(35.dp)
            )
            Text(
                text = "Otvori",
                color = MP_White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // we need to handle the click...
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MP_Pink)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationMenu(
    items: List<BottomNavigationMenuContent>,
    modifier: Modifier = Modifier,
    selectedColor: Color = MP_GreenLight,
    selectedTextColor: Color = MP_White,
    nonActiveTextColor: Color = MP_GreenLight,
    initSelectedItemID: Int = 0 // select first item by default
) {
    var selectedItemID by remember {
        mutableStateOf(initSelectedItemID)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .background(
                Brush.linearGradient(
                    0.0f to Color.DarkGray,
                    1.0f to Color.Black,
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
            .padding(vertical = 10.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationMenuItem(
                item = item,
                isActive = index == selectedItemID,
                selectedColor = selectedColor,
                selectedTextColor = selectedTextColor,
                nonActiveTextColor = nonActiveTextColor
            ) {
                selectedItemID = index // updated
            }
        }
    }
}

@Composable
fun BottomNavigationMenuItem(
    item: BottomNavigationMenuContent,
    isActive: Boolean = false,
    selectedColor: Color = MP_GreenLight,
    selectedTextColor: Color = MP_White,
    nonActiveTextColor: Color = MP_GreenLight,
    onMenuItemClick: () -> Unit // does nothing, void
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
            onMenuItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isActive) {
                        selectedColor
                    } else {
                        Color.Transparent
                    }
                )
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Icon(
                imageVector = item.graphicID,
                contentDescription = item.title,
                tint = if (isActive) selectedTextColor else nonActiveTextColor,
                modifier = Modifier
                    .size(25.dp)
            )
        }
        Text(
            text = item.title,
            style = MaterialTheme.typography.button,
            color = if (isActive) selectedTextColor else nonActiveTextColor,
            modifier = Modifier
                .padding(top = 5.dp)
        )
    }
}