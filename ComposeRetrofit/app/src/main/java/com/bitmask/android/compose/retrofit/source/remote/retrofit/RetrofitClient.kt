package com.bitmask.android.compose.retrofit.source.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.spacexdata.com/v5/"

    private val retrofitService: Retrofit by lazy {

        // 创建日志拦截器
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 设置日志级别
        }

        // 创建 OkHttpClient，并添加日志拦截器
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
           .baseUrl(BASE_URL)
           .client(client)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
    }

    val apiService: SpaceXApiService by lazy {
        retrofitService.create(SpaceXApiService::class.java)
    }
}