package com.triforce.malacprodavac.domain.util.helpers

fun calculateLimitAndOffset(page: Int, perPage: Int) =
    Pair(perPage, (page - 1) * perPage)
