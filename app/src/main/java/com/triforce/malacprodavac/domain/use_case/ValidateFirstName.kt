package com.triforce.malacprodavac.domain.use_case


class ValidateFirstName {
    fun execute(firstName: String):ValidationResult {
        if(firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The first name can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}