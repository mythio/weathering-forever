package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.network.response.Location

interface Repository {

    fun getCurrentWeatherImperial(): LiveData<CurrentWeather>
    fun getCurrentWeatherMetric(): LiveData<CurrentWeather>
    fun getForecastWeatherImperial(): LiveData<List<ForecastWeather>>
    fun getForecastWeatherMetric(): LiveData<List<ForecastWeather>>

    suspend fun getWeather()
    suspend fun searchLocation(location: String): List<Location>
}