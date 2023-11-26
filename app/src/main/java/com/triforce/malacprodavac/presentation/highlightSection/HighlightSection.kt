package com.triforce.malacprodavac.presentation.highlightSection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.triforce.malacprodavac.LinearGradient
import com.triforce.malacprodavac.domain.model.Product
import com.triforce.malacprodavac.presentation.category.ShowcaseProducts
import com.triforce.malacprodavac.presentation.components.RoundedBackgroundComp
import com.triforce.malacprodavac.presentation.components.TitleDescComp
import com.triforce.malacprodavac.presentation.store.components.FilterSortComp
import com.triforce.malacprodavac.presentation.store.components.GoBackComp
import com.triforce.malacprodavac.ui.theme.MP_Black
import com.triforce.malacprodavac.ui.theme.MP_Pink
import com.triforce.malacprodavac.ui.theme.MP_Pink_Dark
import com.triforce.malacprodavac.ui.theme.MP_White

@Composable
fun HighlightSection(
    navController: NavController,
    viewModel: HighlightSectionViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(
        modifier = Modifier
            .background(MP_White)
            .fillMaxSize()
    ) {
        LinearGradient(color1 = MP_Pink, color2 = MP_Pink_Dark)

        RoundedBackgroundComp(top = 65.dp, color = MP_White)

        Column {
            GoBackComp("Više od ${state.currentShop?.businessName}", navController)
            TitleDescComp(
                title = "${state.currentShop?.businessName} za Vaš užitak!",
                description = "Domaćinstvo ${state.currentShop?.businessName} Dostupni od ${state.currentShop?.openFromDays} do ${state.currentShop?.openTillDays} Dana!",
                colorTitle = MP_Black,
                colorDesc = Color.DarkGray
            )
            FilterSortComp(navController)
            ShowcaseProducts(
                products = state.products,
                navController = navController
            )
        }
    }
}