package com.triforce.malacprodavac.presentation.cart.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.presentation.cart.BuyedProducts
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ChooseDateAndTime() {

    val context = LocalContext.current

    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMM yyyy")
                .format(pickedDate)
        }
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = "Izaberi datum preuzimanja")
        }
        Text(text = formattedDate)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Izaberi vreme preuzimanja")
        }
        Text(text = formattedTime)
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "U redu") {
                Toast.makeText(
                    context,
                    "Uspešno izabran datum",
                    Toast.LENGTH_LONG
                )
            }
            negativeButton(text = "Poništi")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Izaberi datum",
            colors = DatePickerDefaults.colors(

            )
        ) {
            pickedDate = it
            BuyedProducts.localDate = pickedDate.toString()
        }
    }

    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "U redu") {
                Toast.makeText(
                    context,
                    "Uspešno izabrano vreme",
                    Toast.LENGTH_LONG
                )
            }
            negativeButton(text = "Poništi")
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Izaberi vreme",
            timeRange = LocalTime.of(7,0)..LocalTime.of(22,0)
        ) {
            pickedTime = it
            BuyedProducts.localTime = pickedTime.toString()
        }
    }

}