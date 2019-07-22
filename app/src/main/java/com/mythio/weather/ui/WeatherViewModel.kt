package com.mythio.weather.ui

import android.app.Application
import androidx.lifecycle.*
import com.mythio.weather.db.getDatabase
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.repository.WeatherRepositoryImpl
import com.mythio.weather.utils.NetworkState
import com.mythio.weather.utils.Unit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherRepository = WeatherRepositoryImpl(getDatabase(application))

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    lateinit var currentWeather: LiveData<CurrentWeather>
    lateinit var forecastWeather: LiveData<List<ForecastWeather>>

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
                Timber.tag("TAG_TAG").e(e)
                _networkState.value = NetworkState.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                return WeatherViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}