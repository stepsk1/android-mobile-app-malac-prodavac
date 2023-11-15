package com.triforce.malacprodavac.domain.util.enum

enum class UserRole(val text: String) {
    Customer("Kupac"),
    Courier("Dostavljač"),
    Shop("Prodavac");

    override fun toString(): String {
        return text
    }
}