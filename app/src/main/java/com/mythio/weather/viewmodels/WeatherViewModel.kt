package com.mythio.weather.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.repository.Repository
import com.mythio.weather.utils.NetworkState
import com.mythio.weather.utils.UNIT_IMPERIAL
import com.mythio.weather.utils.UNIT_METRIC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel(
    private val repository: Repository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var currentWeather: LiveData<CurrentWeather> = MutableLiveData()
    var forecastWeather: LiveData<List<ForecastWeather>> = MutableLiveData()

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    fun getData(location: String, unit: Int) {
        getWeather(location)

        when (unit) {
            UNIT_IMPERIAL -> {
                currentWeather = repository.getCurrentWeatherImperial()
                forecastWeather = repository.getForecastWeatherImperial()
            }
            UNIT_METRIC -> {
                currentWeather = repository.getCurrentWeatherMetric()
                forecastWeather = repository.getForecastWeatherMetric()
            }
        }
    }

    fun refreshData(location: String) {
        getWeather(location)
    }

    fun updateLocation(location: String) {
        getWeather(location)
    }

    private fun getWeather(location: String) {

        viewModelScope.launch {
            _networkState.value = NetworkState.LOADING
            try {
                repository.getWeather(location)
                _networkState.value = NetworkState.FINISH
            } catch (e: IOException) {
                _networkState.value = NetworkState.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}