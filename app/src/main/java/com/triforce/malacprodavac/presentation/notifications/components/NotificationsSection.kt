package com.triforce.malacprodavac.presentation.notifications.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

        val sorted = notifications.sortedWith { left, right ->
            right.createdAt.compareTo(left.createdAt)
        }
        LazyColumn(
            modifier = Modifier
                .requiredHeight(600.dp)

        ) {
            items(sorted) {// how many items do we have
                // define one of items
                Log.i("SDF", it.toString())
                NotificationsItem(
                    notification = it,
                    viewModel = viewModel
                )
            }
        }
    }
}