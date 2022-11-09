package com.example.igormattos.overmovie.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)
