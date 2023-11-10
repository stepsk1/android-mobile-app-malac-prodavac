package com.triforce.malacprodavac.data.services.filter

enum class FilterOperationType {
    // Numeric Operations
    Eq{
        override fun toString(): String {
            return "="
        }
    }
}