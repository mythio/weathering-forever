package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather

interface Repository {

    fun getCurrentWeatherImperial(): LiveData<CurrentWeather>
    fun getCurrentWeatherMetric(): LiveData<CurrentWeather>
    fun getForecastWeatherImperial(): LiveData<List<ForecastWeather>>
    fun getForecastWeatherMetric(): LiveData<List<ForecastWeather>>
}