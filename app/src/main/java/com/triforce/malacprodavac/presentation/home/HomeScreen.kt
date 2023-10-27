package com.triforce.malacprodavac.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.presentation.RegistrationFormEvent
import com.triforce.malacprodavac.presentation.login.LoginFormEvent
import com.triforce.malacprodavac.presentation.login.LoginViewModel
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier
        .background(MP_White)
        .fillMaxSize()
    ){
        Column {
            GreetingSection()
            CategoriesSection(categories = listOf("Malac Pijaca", "Profil", "Porudžbine"))
        }
    }
}

@Composable
fun GreetingSection(
    name: String = "Filip"
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dobrodošao $name!",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Želimo Vam srećnu kupovinu!",
                style = MaterialTheme.typography.body1
            )
        }

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MP_White,
            modifier = Modifier
                .size(24.dp)
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
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
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
                Text(text = categories[it], color = MP_White )
            }
        }
    }
}