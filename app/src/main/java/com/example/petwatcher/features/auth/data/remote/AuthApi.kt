package com.example.petwatcher.features.auth.data.remote

import com.example.petwatcher.core.api.BasicApiResponse
import com.example.petwatcher.features.auth.data.remote.request.CreateAccountRequest
import com.example.petwatcher.features.auth.data.remote.request.LoginRequest
import com.example.petwatcher.features.auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("registration")
    suspend fun registration(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    @GET("authenticate")
    suspend fun authenticate()

    companion object {
        const val BASE_URL = "..."
    }
}