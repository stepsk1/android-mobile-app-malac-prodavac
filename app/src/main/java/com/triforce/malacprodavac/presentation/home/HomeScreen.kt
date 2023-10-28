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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.presentation.RegistrationFormEvent
import com.triforce.malacprodavac.presentation.login.LoginFormEvent
import com.triforce.malacprodavac.presentation.login.LoginViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            CategoriesSection(categories = listOf("Pijaca", "Profil", "Porudžbine", "Korpa", "Prodavnica"))
            GoToStoreSection()
            RecommendedFeaturesSection(
                features = listOf(
                    Feature(
                        title = "Prodavnica",
                        graphicID = Icons.Default.AddCircle,
                        backgroundColor = MP_GreenDark
                    ),
                    Feature(
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        backgroundColor = MP_Orange
                    ),
                    Feature(
                        title = "Omiljeno",
                        graphicID = Icons.Default.Favorite,
                        backgroundColor = MP_GreenLight
                    ),
                    Feature(
                        title = "Korpa",
                        graphicID = Icons.Default.ShoppingCart,
                        backgroundColor = MP_Green
                    ),
                    Feature(
                        title = "Prodavnica",
                        graphicID = Icons.Default.AddCircle,
                        backgroundColor = MP_GreenDark
                    ),
                    Feature(
                        title = "Moj Profil",
                        graphicID = Icons.Default.AccountCircle,
                        backgroundColor = MP_Orange
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
    name: String = "Filip"
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
                text = "Dobrodošao $name!",
                style = MaterialTheme.typography.h4,
                color = MP_Green
            )
            Text(
                text = "Želimo Vam srećnu kupovinu!",
                style = MaterialTheme.typography.body1,
                color = MP_Black
            )
        }

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MP_Black,
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
                    .padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
                    .clickable {
                        selectedCategoryIndex = it // current index of the box
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedCategoryIndex == it)
                            MP_GreenDark
                        else
                            MP_GreenLight
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
    color: Color = MP_Pink
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
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
            text = "Istaknute Akcije",
            style = MaterialTheme.typography.h5,
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
            .padding(7.5.dp)
            .aspectRatio(1F) // ratio is 1x1 so whatever the width is, the hegiht will be the same
            .clip(RoundedCornerShape(10.dp))
            .background(feature.backgroundColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

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
            .background(MP_GreenDark)
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
        modifier = Modifier.clickable {
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