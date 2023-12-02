package com.triforce.malacprodavac.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_GreenDark
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun SortAndFilter(

){
    var showSort by remember { mutableStateOf(false) }
    var showFilter by remember { mutableStateOf(false) }

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ){
        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        showFilter = !showFilter;
                    }
                    .width(90.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Filter",
                    tint = MP_GreenDark,
                    modifier = Modifier
                        .size(21.dp)
                )
                Text(
                    text = "Filtriraj",
                    style = MaterialTheme.typography.body2,
                    color = MP_GreenDark
                )
            }
            AnimatedVisibility(showFilter){
                Column() {
                    Text(
                        text = "Rastuće",
                        style = MaterialTheme.typography.body2,
                        color = MP_Green
                    )
                    Text(
                        text = "Opadajuće",
                        style = MaterialTheme.typography.body2,
                        color = MP_Green
                    )
                }
            }
        }

        Column() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        showSort = !showSort;
                    }
                    .width(90.dp)
            ) {
                Text(
                    text = "Sortriraj",
                    style = MaterialTheme.typography.body2,
                    color = MP_Orange_Dark
                )
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Sort",
                    tint = MP_Orange_Dark,
                    modifier = Modifier
                        .size(21.dp)
                )
            }
            AnimatedVisibility(showSort){
                Column(
                    modifier = Modifier
                        .padding(top = 3.dp)
                ) {
                    Text(
                        text = "Rastuće",
                        style = MaterialTheme.typography.body2,
                        color = MP_Black,
                        modifier = Modifier
                            .padding(top = 6.dp)
                    )
                    Text(
                        text = "Opadajuće",
                        style = MaterialTheme.typography.body2,
                        color = MP_Black,
                        modifier = Modifier
                            .padding(top = 6.dp)
                    )
                }
            }
        }
    }
}