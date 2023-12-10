package com.triforce.malacprodavac.presentation.notifications.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.presentation.notifications.NotificationsState
import com.triforce.malacprodavac.presentation.notifications.NotificationsViewModel

@Composable
fun NotificationsSection(
    notifications: List<Notification>,
    viewModel: NotificationsViewModel
) {
    var state by remember { mutableStateOf(NotificationsState()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .requiredHeight(600.dp)
        ) {
            items(notifications.size) {// how many items do we have
                // define one of items

                NotificationsItem(
                    notification = notifications[notifications.size - it - 1],
                    viewModel = viewModel
                )
            }
        }
    }
}