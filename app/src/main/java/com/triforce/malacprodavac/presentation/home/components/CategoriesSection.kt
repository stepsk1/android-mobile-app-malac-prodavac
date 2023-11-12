package com.triforce.malacprodavac.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White


@Composable
fun CategoriesSection(
    categories: List<String>
) {
    var selectedCategoryIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(categories.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 30.dp, bottom = 5.dp)
                    .shadow(
                        elevation = 5.dp,
                        spotColor = MP_Black,
                        shape = RoundedCornerShape(7.5.dp)
                    )
                    .clickable {
                        selectedCategoryIndex = it // current index of the box
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedCategoryIndex == it)
                            Brush.linearGradient(
                                0.0f to MP_Pink,
                                500.0f to MP_Pink,

                                start = Offset.Zero,
                                end = Offset.Infinite
                            )
                        else
                            Brush.linearGradient(
                                0.0f to MP_Green,
                                0.5f to MP_Green,

                                start = Offset.Zero,
                                end = Offset.Infinite
                            )

                    )
                    .padding(15.dp)
            )
            {
                Text(text = categories[it], color = MP_White)
            }
        }
    }
}