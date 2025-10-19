package com.example.s8107356assessment2.repository

import com.example.s8107356assessment2.model.LoginRequest
import com.example.s8107356assessment2.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val api: ApiService
) {
    suspend fun login(location: String, username: String, password: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.login(location, LoginRequest(username, password))
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!.keypass)
                } else {
                    Result.failure(Exception("Login failed: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
