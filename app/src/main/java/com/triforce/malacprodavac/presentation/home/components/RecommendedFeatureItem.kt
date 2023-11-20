package com.triforce.malacprodavac.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.triforce.malacprodavac.Feature
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White


@Composable
fun RecommendedFeatureItem(
    navController: NavController,
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(6.dp)
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
            )
            .padding(1.5.dp)
            .aspectRatio(5F) // ratio is 1x1 so whatever the width is, the hegiht will be the same
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.linearGradient(
                    0.0f to feature.color1,
                    0.5f to feature.color2,

                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate(feature.screen.route)
                }
                .padding(15.dp)
        ) {
            Icon(
                imageVector = feature.graphicID,
                contentDescription = feature.title,
                tint = MP_White,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(50.dp)
            )
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h6,
                lineHeight = 25.sp,
                textAlign = TextAlign.Center,
                color = MP_White,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }
    }
}