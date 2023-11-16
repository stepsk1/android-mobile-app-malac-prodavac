package com.triforce.malacprodavac.domain.use_case.validate

import java.util.regex.Pattern


class ValidatePhoneNumber {
    fun execute(phoneNumber: String): ValidationResult {
        if (!Pattern.compile("^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}\$")
                .matcher(phoneNumber).find()
        ) {
            return ValidationResult(
                successful = false,
                errorMessage = "Mora biti validan telefonski broj!"
            )
        }

        return ValidationResult(successful = true)
    }
}