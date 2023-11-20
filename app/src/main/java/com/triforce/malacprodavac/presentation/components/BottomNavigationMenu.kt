package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.triforce.malacprodavac.ui.theme.MP_White
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_GreenLight
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark

@Composable
fun BottomNavigationMenu(
    navController: NavController,
    items: List<BottomNavigationMenuContent>,
    modifier: Modifier = Modifier,
    selectedColor: Color = MP_Orange_Dark,
    selectedTextColor: Color = MP_Orange_Dark,
    nonActiveTextColor: Color = MP_Green,
    initSelectedItemID: Int = 0 // select first item by default
) {
    var selectedItemID by remember {
        mutableStateOf(initSelectedItemID)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                spotColor = MP_Black,
                ambientColor = MP_Green
            )
            .padding(top = 5.dp)
            .fillMaxWidth()
            .background(MP_White)
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
