package com.triforce.malacprodavac.domain.util.enum

enum class Currency(val text: String) {
    RSD("RSD");

    override fun toString(): String {
        return text
    }
}