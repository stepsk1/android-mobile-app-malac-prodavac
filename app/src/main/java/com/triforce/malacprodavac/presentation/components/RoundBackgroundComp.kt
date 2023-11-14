package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.material.Surface
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun RoundedBackgroundComp(
    top: Dp,
    color: Color,
) {
    Surface(
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = top)
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
    ) {

    }
}