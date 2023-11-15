package com.triforce.malacprodavac.domain.use_case.validate

import java.util.regex.Pattern

class ValidateLastName {
    fun execute(lastName: String): ValidationResult {
        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Prezime ne može biti prazno"
            )
        }
        if (!Pattern.compile(
                "^[\\p{L} ,.'-]+$",
                Pattern.CASE_INSENSITIVE
            )
                .matcher(lastName).matches()
        ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Prezime može sadržati samo slova, zarez, tačku, apostrof i srednju crtu!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}