package com.bitmask.android.compose.retrofit.model

/**
 * 发射信息
 */
data class Launch(
    val id: String,
    val data_local: String,
    val details: String,
    val success: Boolean,
    val rocket: String
)
