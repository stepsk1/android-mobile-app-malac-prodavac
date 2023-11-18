package com.triforce.malacprodavac.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Green
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_White
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun Confirmation() {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 5.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                spotColor = MP_Black,
                shape = RoundedCornerShape(7.5.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .background(MP_White)
            .padding(vertical = 20.dp, horizontal = 20.dp)
    ){
        Column(
            modifier = Modifier.padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {


            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "CheckCircle",
                tint = MP_Green,
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Vaša kupovina je uspešno završena",
                style = MaterialTheme.typography.h5,
                color = MP_Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Kod za praćenje:",
                style = MaterialTheme.typography.h6,
                color = MP_Black,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "0001000012F",
                style = MaterialTheme.typography.h6,
                color = MP_Pink,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Datum potvrde",
                style = MaterialTheme.typography.h6,
                color = MP_Black,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )

            val calender = Calendar.getInstance()

            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH) + 1
            val day = calender.get(Calendar.DAY_OF_MONTH)
            val hour = calender.get(Calendar.HOUR)
            val min = calender.get(Calendar.MINUTE)
            val date = "$day-$month-$year $hour:$min"

            Text(
                text = date,
                style = MaterialTheme.typography.h6,
                color = MP_Pink,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}