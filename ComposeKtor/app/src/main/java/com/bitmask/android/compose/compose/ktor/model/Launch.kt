package com.bitmask.android.compose.compose.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    val id: String?,
//    val data_local: String?,
    val details: String?,
    val success: Boolean?,
    val rocket: String?
)
