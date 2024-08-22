package com.bitmask.android.compose.retrofit.source.remote.repository

import com.bitmask.android.compose.retrofit.model.Launch

interface SpaceXRepository {
    suspend fun getLaunches(): List<Launch>
}