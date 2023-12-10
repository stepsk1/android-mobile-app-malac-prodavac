package com.triforce.malacprodavac.presentation.transactions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triforce.malacprodavac.domain.model.Order
import com.triforce.malacprodavac.presentation.transactions.TransactionViewModel

@Composable
fun TransactionSection(
    transactions: List<Order>,
    viewModel: TransactionViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .requiredHeight(500.dp)
        ) {
            items(transactions.size) {
                TransactionItem(
                    transaction = transactions[transactions.size - it - 1],
                    viewModel = viewModel,
                    numberTransaction = it + 1,
                    navController = navController
                )
            }
        }
    }
}