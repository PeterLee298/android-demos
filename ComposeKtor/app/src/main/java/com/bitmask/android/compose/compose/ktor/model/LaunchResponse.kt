package com.bitmask.android.compose.compose.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class LaunchResponse(
    val launches: List<Launch>
)