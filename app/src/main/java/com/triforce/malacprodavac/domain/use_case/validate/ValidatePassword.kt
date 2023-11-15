package com.triforce.malacprodavac.domain.use_case.validate

import java.util.regex.Pattern

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (!Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\W]{8,}$").matcher(password)
                .find()
        ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Šifra mora da bude duga 8 karaktera i sadrži bar jedan broj!"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}