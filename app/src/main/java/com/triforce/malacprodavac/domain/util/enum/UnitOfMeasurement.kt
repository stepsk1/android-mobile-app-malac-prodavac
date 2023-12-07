package com.triforce.malacprodavac.domain.util.enum

enum class UnitOfMeasurement(val text: String) {
    KG("KG"), G("G"), L("L"), ML("ML"), PCS("PCS");

    override fun toString(): String {
        return text
    }

}