package com.mythio.weather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mythio.weather.Unit
import com.mythio.weather.db.getDatabase
import com.mythio.weather.db.model.domain.current.CurrentWeatherImperial
import com.mythio.weather.db.model.domain.current.CurrentWeatherMetric
import com.mythio.weather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherRepository = WeatherRepository(getDatabase(application))

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var currentWeatherImperial: LiveData<CurrentWeatherImperial>? = null
    var currentWeatherMetric: LiveData<CurrentWeatherMetric>? = null

    init {
        viewModelScope.launch {
            weatherRepository.getWeatherForecast()
        }
    }

    fun getValuesOfUnit(unit: Unit) {
        when (unit) {
            Unit.METRIC -> currentWeatherImperial = weatherRepository.getCurrentWeatherImperial()
            Unit.IMPERIAL -> currentWeatherMetric = weatherRepository.getCurrentWeatherMetric()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                return MainActivityViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}