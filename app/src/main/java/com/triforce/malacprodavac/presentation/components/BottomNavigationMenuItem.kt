package com.triforce.malacprodavac.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import com.triforce.malacprodavac.ui.theme.MP_White
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.BottomNavigationMenuContent
import com.triforce.malacprodavac.ui.theme.MP_GreenLight


@Composable
fun BottomNavigationMenuItem(
    navController: NavController,
    item: BottomNavigationMenuContent,
    isActive: Boolean = false,
    selectedColor: Color = MP_GreenLight,
    selectedTextColor: Color = MP_White,
    nonActiveTextColor: Color = MP_GreenLight,
    onMenuItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable {
                navController.navigate(item.screen.route)
                item.isActive = true
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (item.isActive) {
                        selectedColor
                    } else {
                        Color.Transparent
                    }
                )
                .clickable {
                    navController.navigate(item.screen.route)
                    item.isActive = true
                }
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Icon(
                imageVector = item.graphicID,
                contentDescription = item.title,
                tint = if (item.isActive) selectedTextColor else nonActiveTextColor,
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