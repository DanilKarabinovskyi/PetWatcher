package com.example.petwatcher.features.auth.data.remote.request

data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)
