package com.triforce.malacprodavac.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = MP_Pink,
    onClick: ((Int) -> Unit)? = null
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
//    val halfStar = !(rating.rem(1).equals(0.0))

    var ratingState by remember { mutableIntStateOf(filledStars) }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(),
    ) {
        repeat(stars) { index ->
            if (index < ratingState)
                IconButton(
                    onClick = {
                        if (onClick != null)
                            onClick(index + 1).let {
                                ratingState = index + 1
                            }
                    },
                    enabled = onClick != null
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = starsColor,
                        modifier = Modifier.size(26.dp)
                    )
                }
            else if (index in (ratingState)..<stars - unfilledStars) {
                IconButton(
                    onClick = {
                        if (onClick != null)
                            onClick(index + 1).let {
                                ratingState = index + 1
                            }
                    },
                    enabled = onClick != null
                ) {
                    Icon(
                        imageVector = Icons.Outlined.StarHalf,
                        contentDescription = null,
                        tint = starsColor,
                        modifier = Modifier.size(26.dp)
                    )
                }
            } else
                IconButton(
                    onClick = {
                        if (onClick != null)
                            onClick(index + 1).let {
                                ratingState = index + 1
                            }
                    },
                    enabled = onClick != null

                ) {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = null,
                        tint = starsColor,
                        modifier = Modifier.size(26.dp)
                    )
                }
        }
    }
}