package com.triforce.malacprodavac.presentation.notifications.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.presentation.notifications.NotificationsViewModel

@Composable
fun NotificationsSection(
    isLoading: Boolean = false,
    isLastPage: Boolean = false,
    loadNext: () -> Unit,
    notifications: List<Notification>,
    viewModel: NotificationsViewModel
) {

    val scrollState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(state = scrollState) {
            items(notifications) {
                NotificationsItem(
                    notification = it,
                    viewModel = viewModel
                )
            }
            item {
                if (isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LaunchedEffect(scrollState.canScrollForward) {
                        if (!isLastPage && !scrollState.canScrollForward) {
                            loadNext()
                        }
                    }
                }
            }
        }
    }
}