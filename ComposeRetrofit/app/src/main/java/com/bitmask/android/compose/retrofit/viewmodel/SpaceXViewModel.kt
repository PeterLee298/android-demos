package com.bitmask.android.compose.retrofit.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmask.android.compose.retrofit.model.Launch
import com.bitmask.android.compose.retrofit.source.remote.repository.SpaceXRepositoryImpl
import kotlinx.coroutines.launch

class SpaceXViewModel: ViewModel() {
    private val repository = SpaceXRepositoryImpl()
    private val _launches = mutableStateOf<List<Launch>?>(null)
    val launches: State<List<Launch>?> get()  = _launches

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