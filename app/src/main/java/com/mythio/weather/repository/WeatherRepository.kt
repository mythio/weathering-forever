package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.db.model.domain.current.CurrentWeatherImperial
import com.mythio.weather.db.model.domain.current.CurrentWeatherMetric
import com.mythio.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    private val weatherImp = database.weatherDao.getCurrentWeatherImperial()
    private val weatherMet = database.weatherDao.getCurrentWeatherMetric()

    fun getCurrentWeatherImperial(): LiveData<CurrentWeatherImperial> {
        return weatherImp
    }

    fun getCurrentWeatherMetric(): LiveData<CurrentWeatherMetric> {
        return weatherMet
    }

    suspend fun getWeatherForecast() {
        withContext(Dispatchers.IO) {
            val weather = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
                .await()
            database.weatherDao.upsert(weather.current)
        }
    }
}
