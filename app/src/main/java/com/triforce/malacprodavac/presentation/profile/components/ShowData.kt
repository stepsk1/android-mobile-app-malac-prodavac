package com.triforce.malacprodavac.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Gray

@Composable
fun ShowData(
    title: String,
    data: String,
    contentDescription: String,
    icon: ImageVector
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        lineHeight = 15.sp,
        color = MP_Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 15.dp, top = 30.dp)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(MP_Gray)
            .padding(5.dp) )
    {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MP_Black,
            modifier = Modifier
                .align(Alignment.TopStart)
        )

        Text(
            text = data,
            style = MaterialTheme.typography.h6,
            lineHeight = 15.sp,
            color = MP_Black,
            modifier = Modifier
                .padding(start = 15.dp)
                .align(Alignment.BottomCenter)
        )
    }
}