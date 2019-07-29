package com.mythio.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mythio.weather.model.entity.Location
import com.mythio.weather.repository.Repository
import com.mythio.weather.utils.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository
) : ViewModel() {

    val isDataAvailable = MutableLiveData(false)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val searchLocationQuery = MutableLiveData<String>()

    var recentLocations: LiveData<List<Location>> = MutableLiveData()

    private var _searchLocations = MutableLiveData<List<Location>>()
    val searchLocations: LiveData<List<Location>>
        get() = _searchLocations

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    init {
        attachObserver()
    }

    fun getSearchData(location: String) {
        _networkState.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                _searchLocations.value = repository.searchLocation(location)
                isDataAvailable.value = searchLocations.value?.isNotEmpty()
                _networkState.value = NetworkState.FINISH
            } catch (e: Exception) {
                _networkState.value = NetworkState.ERROR
            }
        }
    }

    fun add(location: Location) {
        viewModelScope.launch {
            repository.addRecentLocations(location)
        }
    }

    fun delete(location: Location) {
        viewModelScope.launch {
            repository.deleteRecentLocations(location)
        }
    }

    private fun attachObserver() {
        recentLocations = repository.getRecentLocations()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        _searchLocations.value = emptyList()
        searchLocationQuery.value = emptySequence<String>().toString()
    }
}