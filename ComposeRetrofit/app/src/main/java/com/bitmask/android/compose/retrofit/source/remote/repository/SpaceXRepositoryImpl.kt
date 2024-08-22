package com.bitmask.android.compose.retrofit.source.remote.repository

import com.bitmask.android.compose.retrofit.model.Launch
import com.bitmask.android.compose.retrofit.source.remote.retrofit.RetrofitClient

class SpaceXRepositoryImpl: SpaceXRepository {
    override suspend fun getLaunches(): List<Launch> {
        return RetrofitClient.apiService.getLaunches()
    }
}