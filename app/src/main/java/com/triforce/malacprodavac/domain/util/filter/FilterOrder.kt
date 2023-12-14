package com.triforce.malacprodavac.domain.util.filter

enum class FilterOrder(val order: String) {
    Asc("asc"),
    Desc("desc");

    override fun toString(): String {
        return this.order
    }
}