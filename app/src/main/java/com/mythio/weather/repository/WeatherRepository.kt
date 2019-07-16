package com.mythio.weather.repository

import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    val weather = database.weatherDao.getWeatherMetric()

    suspend fun get() {

        withContext(Dispatchers.IO) {
            val weatherAPI = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
                .await()
            database.weatherDao.upsert(weatherAPI.current)
        }
    }
}
