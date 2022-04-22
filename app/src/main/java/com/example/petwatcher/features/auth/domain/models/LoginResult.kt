package com.example.petwatcher.features.auth.domain.models

import com.example.petwatcher.core.api.SimpleResource
import com.example.petwatcher.features.auth.presentation.util.AuthError

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
