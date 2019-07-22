package com.mythio.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.repository.WeatherRepositoryImpl
import com.mythio.weather.utils.NetworkState
import com.mythio.weather.utils.Unit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel(
    private val weatherRepository: WeatherRepositoryImpl
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var currentWeather: LiveData<CurrentWeather> = MutableLiveData()
    var forecastWeather: LiveData<List<ForecastWeather>> = MutableLiveData()

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    fun getValuesOfUnit(unit: Unit) {
        when (unit) {
            Unit.IMPERIAL -> {
                currentWeather = weatherRepository.getCurrentWeatherImperial()
                forecastWeather = weatherRepository.getForecastWeatherImperial()
            }
            Unit.METRIC -> {
                currentWeather = weatherRepository.getCurrentWeatherMetric()
                forecastWeather = weatherRepository.getForecastWeatherMetric()
            }
        }

        getWeather()
    }

    fun refreshData() {
        getWeather()
    }

    private fun getWeather() {

        viewModelScope.launch {
            _networkState.value = NetworkState.LOADING
            try {
                weatherRepository.getWeather()
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