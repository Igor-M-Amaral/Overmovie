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

}