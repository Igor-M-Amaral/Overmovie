package com.example.igormattos.overmovie.utils.methods

import com.example.igormattos.overmovie.data.model.ValidationResult

object UtilsMethods {

    fun validationLogin(email: String, password: String): ValidationResult {
        if (email.isBlank() && password.isBlank()) return ValidationResult(
            false,
            "Email and password cannot be blank"
        )
        if (email.isBlank() || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) return ValidationResult(false, "Invalid login email")
        if (password.isBlank() || password.isEmpty()) return ValidationResult(false, "Invalid login password")
        return ValidationResult(true)
    }

    fun validationRegister(email: String, password: String, confirm: String): ValidationResult {
        if (email.isBlank() && password.isBlank() && confirm.isBlank()) return ValidationResult(
            false,
            "Email and password cannot be blank"
        )
        if (email.isBlank() || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) return ValidationResult(false, "Invalid login email")
        if (password.isBlank() || password.isEmpty() && confirm.isBlank() || confirm.isEmpty()) return ValidationResult(false, "Invalid login password")

        if (password != confirm) return ValidationResult(false, "Your password and confirmation password do not match")

        if (password.length < 6) return ValidationResult(false, "Your password must be at least 6 characters")

        return ValidationResult(true)
    }

}