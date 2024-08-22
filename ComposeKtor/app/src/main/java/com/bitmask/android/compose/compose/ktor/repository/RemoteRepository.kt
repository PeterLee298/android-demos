package com.bitmask.android.compose.compose.ktor.repository

import com.bitmask.android.compose.compose.ktor.model.Launch

interface RemoteRepository {
    suspend fun getLaunches(): Result<List<Launch>>
}