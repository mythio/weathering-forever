package com.mythio.weather.repository

import android.util.Log
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val database: WeatherDatabase) {

    val weather = database.weatherDao.getCurrentWeatherMetric()
    val weatherForecast = database.weatherDao.getForecastMetric()

    suspend fun get() {

        withContext(Dispatchers.IO) {
            val weatherAPI = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
                .await()
            Log.d("TAG_TAG_TAG", weatherAPI.forecast.forecastday[0].day.condition.text)
            database.weatherDao.upsertForecast(weatherAPI.forecast.forecastday)
            database.weatherDao.upsert(weatherAPI.current)
        }
    }
}
