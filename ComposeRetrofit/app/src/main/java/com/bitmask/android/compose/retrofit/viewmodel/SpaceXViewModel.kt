package com.bitmask.android.compose.retrofit.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmask.android.compose.retrofit.model.Launch
import com.bitmask.android.compose.retrofit.source.remote.repository.SpaceXRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceXViewModel: ViewModel() {
    private val repository = SpaceXRepositoryImpl()
    private val _launches = MutableStateFlow<List<Launch>>(emptyList())
    val launches: StateFlow<List<Launch>> = _launches.asStateFlow()

    init {
        getLaunches()
    }

    fun getLaunches() {
        viewModelScope.launch {
            try {
                _launches.value = repository.getLaunches()
            } catch (e: Exception) {
                Log.e("SpaceXViewModel", "Error fetching launches: $e")
            }
        }
    }
}