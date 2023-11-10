package com.triforce.malacprodavac.data.services.filter

enum class FilterOrder {
    asc{
        override fun toString(): String {
            return "asc"
        }
    },
    desc{
        override fun toString(): String {
            return "desc"
        }
    }
}