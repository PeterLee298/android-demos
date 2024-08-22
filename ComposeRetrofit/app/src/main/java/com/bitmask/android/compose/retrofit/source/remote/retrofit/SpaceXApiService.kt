package com.bitmask.android.compose.retrofit.source.remote.retrofit

import com.bitmask.android.compose.retrofit.model.Launch
import retrofit2.http.GET

interface SpaceXApiService {
    @GET("launches")
    suspend fun getLaunches(): List<Launch>
}