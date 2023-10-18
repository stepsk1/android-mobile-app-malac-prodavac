package com.triforce.malacprodavac.domain.use_case

class ValidateLastName {
    fun execute(lastName: String):ValidationResult {
        if(lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The last name can't be blank"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}