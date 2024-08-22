package com.bitmask.android.compose.compose.ktor.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {
    companion object{
        private const val BASE_URL = "https://api.spacexdata.com/v5/"
    }

    suspend fun getLaunches() = client.get(BASE_URL + "launches")
}