package com.mythio.weather.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mythio.weather.network.response.LocationResponse
import com.mythio.weather.repository.Repository
import kotlinx.coroutines.*

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val string = MutableLiveData<String>()

    private val _searchResults = repository.searchResponse
    val searchResults: LiveData<List<LocationResponse>>
        get() = _searchResults

    fun getSearchData(location: String) {
        viewModelScope.launch {
            repository.searchLocation(location)
        }
    }
}