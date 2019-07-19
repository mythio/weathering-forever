package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.network.WeatherApi
import com.mythio.weather.network.response.Location
import com.mythio.weather.utils.convert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val database: WeatherDatabase
) : Repository {

    override fun getCurrentWeatherImperial(): LiveData<CurrentWeather> {
        return Transformations
            .map(database.weatherDao.getCurrentWeatherImperial()) { weather ->
                weather?.convert()
            }
    }

    override fun getCurrentWeatherMetric(): LiveData<CurrentWeather> {
        return Transformations
            .map(database.weatherDao.getCurrentWeatherMetric()) { weather ->
                weather?.convert()
            }
    }

    override fun getForecastWeatherImperial(): LiveData<List<ForecastWeather>> {
        return Transformations
            .map(database.weatherDao.getForecastImperial()) { weatherList ->
                weatherList.map {
                    it.convert()
                }
            }
    }

    override fun getForecastWeatherMetric(): LiveData<List<ForecastWeather>> {
        return Transformations
            .map(database.weatherDao.getForecastMetric()) { weatherList ->
                weatherList.map {
                    it.convert()
                }
            }
    }

    override suspend fun searchLocation(location: String): List<Location> {
        return WeatherApi
            .retrofitService
            .getSearchLocationAsync("70ef3b7f24484a918b782502191207", location)
            .body()!!
    }

    override suspend fun getWeather() {
        withContext(Dispatchers.IO) {
            val response = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
            if (response.isSuccessful) {
                val data = response.body()!!
                data.current.location = data.location
                database.weatherDao.upsertCurrentWeather(data.current)
                database.weatherDao.upsertForecastWeather(data.forecast.forecastday)
            }
        }
    }
}
