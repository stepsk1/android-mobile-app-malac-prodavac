package com.triforce.malacprodavac.presentation.product.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.triforce.malacprodavac.presentation.components.RatingStars
import com.triforce.malacprodavac.ui.theme.MP_Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReviewDialog(closeDialog: () -> Unit, onSubmit: (String, Int) -> Unit) {

    var text by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }
    val setText = { newText: String ->
        text = newText
    }
    val setRating = { newRating: Int ->
        rating = newRating
    }

    Dialog(
        onDismissRequest = closeDialog,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Oceni Proizvod",
                    style = MaterialTheme.typography.h5,
                    color = MP_Black,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )

                Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
                    TextField(
                        value = text,
                        onValueChange = setText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        placeholder = {
                            androidx.compose.material3.Text(text = "Tekst")
                        }
                    )
                    Spacer(Modifier.height(16.dp))
                    RatingStars(modifier = Modifier.fillMaxWidth(), onClick = setRating)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            closeDialog()
                            onSubmit(text, rating)
                        },
                        modifier = Modifier
                            .width(50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Color.Green
                            )
                    ) {
                        Icon(Icons.Filled.Check, contentDescription = "Potvrdi")
                    }
                }
            }

        }
    }

}