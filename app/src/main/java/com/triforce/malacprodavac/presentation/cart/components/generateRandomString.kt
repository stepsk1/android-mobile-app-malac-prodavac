package com.triforce.malacprodavac.presentation.cart.components

import kotlin.random.Random

fun generateRandomString(length: Int): String {
    val charPool: List<Char> = ('A'..'Z') + ('0'..'9')
    val random = Random

    return (1..length)
        .map { random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}