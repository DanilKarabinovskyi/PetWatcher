package com.example.petwatcher.features.auth.data.repository

import android.content.SharedPreferences
import com.example.petwatcher.core.api.Resource
import com.example.petwatcher.core.api.SimpleResource
import com.example.petwatcher.features.auth.data.remote.AuthApi
import com.example.petwatcher.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        return Resource.Success(Unit)
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        return Resource.Success(Unit)
    }

    override suspend fun authenticate(): SimpleResource {
        return Resource.Success(Unit)
    }
}