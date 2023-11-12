package com.triforce.malacprodavac.presentation.store.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun FilterSortComp(
    navController: NavController
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 15.dp
            )
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(
                    width =  75.dp,
                    height = 20.dp
                )
                .clickable {
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Filtriraj",
                tint = MP_White,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "Filtriraj",
                style = MaterialTheme.typography.body2,
                color = MP_White,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(
                    width =  75.dp,
                    height = 20.dp
                )
                .clickable {
                }

        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Sortiraj",
                tint = MP_White,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "Sortiraj",
                style = MaterialTheme.typography.body2,
                color = MP_White,
            )
        }
    }
}