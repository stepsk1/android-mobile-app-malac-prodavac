package com.triforce.malacprodavac.presentation.cart.CartDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.presentation.cart.BuyedProductSection
import com.triforce.malacprodavac.presentation.store.HeaderSectionTitle
import com.triforce.malacprodavac.ui.theme.MP_Orange
import com.triforce.malacprodavac.ui.theme.MP_Orange_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun CartDetailsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_Orange, color2 = MP_Orange_Dark)
        Surface(
            color = MP_White,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1F)
                .padding(top = 67.dp)
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
        ) {

        }
        Column {
            HeaderSectionTitle("Detalji plaćanja", navController)
        }
    }
}