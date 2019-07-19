package com.mythio.weather.ui

import android.app.Application
import androidx.lifecycle.*
import com.mythio.weather.db.getDatabase
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.network.response.Location
import com.mythio.weather.repository.WeatherRepositoryImpl
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

    private val _weatherResult = MutableLiveData<CurrentWeather>()
    val weatherResult: LiveData<CurrentWeather>
        get() = _weatherResult

    private val _searchResult = MutableLiveData<List<Location>>()
    val searchResult: LiveData<List<Location>>
        get() = _searchResult

    init {
        getWeather()
        search()
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                weatherRepository.getWeather()
            } catch (e: IOException) {
                Timber.tag("TAG_TAG").e(e)
            }
        }
    }

    private fun search() {
        viewModelScope.launch {
            try {
                _searchResult.value = weatherRepository.searchLocation("merces")
            } catch (e: Exception) {

            }
        }
    }

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