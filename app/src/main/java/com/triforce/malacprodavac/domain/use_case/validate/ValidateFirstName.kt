package com.triforce.malacprodavac.domain.use_case.validate

import java.util.regex.Pattern


class ValidateFirstName {
    fun execute(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Ime ne može biti prazno"
            )
        }
        if (!Pattern.compile(
                "^[\\p{L} ,.'-]+$",
                Pattern.CASE_INSENSITIVE
            )
                .matcher(firstName).matches()
        ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Ime može sadržati samo slova, zarez, tačku, apostrof i srednju crtu!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}