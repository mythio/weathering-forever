package com.mythio.weather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mythio.weather.db.entity.CurrentWeather
import com.mythio.weather.network.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

enum class ApiStatus {
    LOADING, ERROR, DONE
}

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _weather = MutableLiveData<CurrentWeather>()
    val weather: LiveData<CurrentWeather>
        get() = _weather

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        val data = WeatherApi.retrofitService.getWeather("70ef3b7f24484a918b782502191207", "panaji", 7)
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                _weather.value = data.await().current
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}