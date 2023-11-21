package com.triforce.malacprodavac.presentation.transactions

import com.triforce.malacprodavac.domain.model.Order

data class TransactionState(

    val transactions: List<Order> = emptyList(),
    val isLoading: Boolean = false
)
