package com.mythio.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mythio.weather.model.entity.Location
import com.mythio.weather.repository.Repository
import kotlinx.coroutines.*

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    val isDataAvailable = MutableLiveData(false)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    var locationList: LiveData<List<Location>> = repository.getLocation()

    val location = MutableLiveData<String>()

    private val _searchResults = repository.searchResponse
    val searchResults: LiveData<List<Location>>
        get() = _searchResults

    fun getSearchData(location: String) {
        viewModelScope.launch {
            repository.searchLocation(location)
            isDataAvailable.value = _searchResults.value?.isNotEmpty()
        }
    }

    fun add(location: Location) {
        GlobalScope.launch {
            repository.add(location)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        _searchResults.value = emptyList()
        location.value = emptySequence<String>().toString()
    }
}