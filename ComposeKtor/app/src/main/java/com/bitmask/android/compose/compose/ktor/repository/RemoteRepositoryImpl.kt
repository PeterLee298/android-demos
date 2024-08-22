package com.bitmask.android.compose.compose.ktor.repository

import com.bitmask.android.compose.compose.ktor.model.Launch
import com.bitmask.android.compose.compose.ktor.model.LaunchResponse
import com.bitmask.android.compose.compose.ktor.network.ApiService
import io.ktor.client.call.body

class RemoteRepositoryImpl(
    private val apiService: ApiService
): RemoteRepository {
    override suspend fun getLaunches(): Result<List<Launch>> =
        kotlin.runCatching {
            apiService.getLaunches().body<List<Launch>>()
        }
}