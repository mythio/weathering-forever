package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.db.model.domain.CurrentWeather
import com.mythio.weather.db.model.domain.convert
import com.mythio.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    private val weatherImperial: LiveData<CurrentWeather> =
        Transformations.map(database.weatherDao.getCurrentWeatherImperial()) { weather ->
            weather.convert()
        }

    private val weatherMetric: LiveData<CurrentWeather> =
        Transformations.map(database.weatherDao.getCurrentWeatherMetric()) { weather ->
            weather.convert()
        }

    fun getCurrentWeatherImperial(): LiveData<CurrentWeather> {
        return weatherImperial
    }

    fun getCurrentWeatherMetric(): LiveData<CurrentWeather> {
        return weatherMetric
    }

    suspend fun getWeatherForecast() {
        withContext(Dispatchers.IO) {
            val response = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
            if (response.isSuccessful) {
                database.weatherDao.upsertCurrentWeather(response.body()!!.current)
            } else {
                throw Exception()
            }
        }
    }
}
