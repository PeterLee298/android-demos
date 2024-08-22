package com.bitmask.android.compose.compose.ktor.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmask.android.compose.compose.ktor.model.Launch
import com.bitmask.android.compose.compose.ktor.network.ApiService
import com.bitmask.android.compose.compose.ktor.network.httpClientAndroid
import com.bitmask.android.compose.compose.ktor.repository.RemoteRepository
import com.bitmask.android.compose.compose.ktor.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceXViewModel: ViewModel() {
    private val apiService = ApiService(httpClientAndroid)
    private val repository = RemoteRepositoryImpl(apiService)

    private val _launches = MutableStateFlow<List<Launch>>(emptyList())
    val launches: StateFlow<List<Launch>> = _launches

//    init {
//        getLaunches()
//    }

    suspend fun getLaunches() {
        viewModelScope.launch {
            repository.getLaunches().fold(
                onSuccess = { launches ->
                    _launches.value = launches
                },
                onFailure = { error ->
                    Log.e("SpaceXViewModel", "Error fetching launches: $error")
                }
            )
        }
    }
}