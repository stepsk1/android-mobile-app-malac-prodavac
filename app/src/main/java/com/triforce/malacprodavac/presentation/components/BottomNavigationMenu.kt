package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.triforce.malacprodavac.ui.theme.MP_White
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.ui.theme.MP_GreenLight

@Composable
fun BottomNavigationMenu(
    navController: NavController,
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
                navController = navController,
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
