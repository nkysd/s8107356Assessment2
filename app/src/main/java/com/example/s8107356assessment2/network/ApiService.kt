package com.example.s8107356assessment2.network

import com.example.s8107356assessment2.model.LoginRequest
import com.example.s8107356assessment2.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("{location}/auth")
    suspend fun login(
        @Path("location") location: String,
        @Body request: LoginRequest
    ): Response<LoginResponse>
}
