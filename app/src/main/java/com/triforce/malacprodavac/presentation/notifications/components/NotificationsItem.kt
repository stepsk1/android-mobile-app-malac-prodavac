package com.triforce.malacprodavac.presentation.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.domain.model.notifications.Notification
import com.triforce.malacprodavac.presentation.notifications.NotificationsViewModel
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun NotificationsItem(
    notification: Notification,
    viewModel: NotificationsViewModel
){

    var createdAt: String = notification.createdAt
    var dateOfCreating: String = createdAt.split("T")[0]
    var day = dateOfCreating.split("-")[2]
    var month = dateOfCreating.split("-")[1]
    var year = dateOfCreating.split("-")[0]
    var time: String = createdAt.split("T")[1]
    time = time.split(".")[0]
    dateOfCreating = day + "." + month + "." + year

    BoxWithConstraints(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(3.5.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(MP_White)
            .fillMaxWidth()
            .requiredHeight(120.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                text = dateOfCreating + "  " + time,
                style = MaterialTheme.typography.body1,
                color = MP_Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            )

            Text(
                text = notification.notificationPayload.payload.data.title,
                style = MaterialTheme.typography.body1,
                color = MP_Black,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
        }
    }
}