package com.triforce.malacprodavac.presentation.highlightSection.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.navigation.NavController
import com.triforce.malacprodavac.presentation.category.CategoryEvent
import com.triforce.malacprodavac.presentation.category.CategoryViewModel
import com.triforce.malacprodavac.presentation.highlightSection.HighlightSectionEvent
import com.triforce.malacprodavac.presentation.highlightSection.HighlightSectionViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun SortAndFilterCategoryProducts(
    navController: NavController,
    viewModel: CategoryViewModel
) {
    var showSort by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(90.dp)
                .clickable {
                    showSort = !showSort;
                }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Sort",
                tint = MP_White,
                modifier = Modifier
                    .size(21.dp)
            )
            Text(
                text = "Sortriraj",
                style = MaterialTheme.typography.body1,
                color = MP_White
            )
        }
        AnimatedVisibility(showSort) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(170.dp)
            ){
                Text(
                    text = "Rastuće",
                    style = MaterialTheme.typography.body1,
                    color = MP_White,
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(CategoryEvent.OrderBy(1))
                        }
                )
                Text(
                    text = "Opadajuće",
                    style = MaterialTheme.typography.body1,
                    color = MP_White,
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(CategoryEvent.OrderBy(2))
                        }
                )
            }
        }
    }
}