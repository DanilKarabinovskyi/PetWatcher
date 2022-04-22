package com.example.petwatcher.features.auth.domain.use_case

import com.example.petwatcher.core.api.SimpleResource
import com.example.petwatcher.features.auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}