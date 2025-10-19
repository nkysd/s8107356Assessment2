package com.example.s8107356assessment2.repository

import com.example.s8107356assessment2.model.Entity
import com.example.s8107356assessment2.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DashboardRepository(
    private val api: ApiService
) {
    suspend fun getDashboardData(keypass: String): Result<List<Entity>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getDashboardData(keypass)
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!.entities)
                } else {
                    Result.failure(Exception("Dashboard API error: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
