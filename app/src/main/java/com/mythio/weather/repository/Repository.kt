package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mythio.weather.db.dao.WeatherDao
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.model.entity.Location
import com.mythio.weather.network.WeatherApi
import com.mythio.weather.utils.API_KEY
import com.mythio.weather.utils.convert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository private constructor(
    private val weatherDao: WeatherDao
) {

    fun getCurrentWeatherImperial(): LiveData<CurrentWeather> {
        return Transformations
            .map(weatherDao.getCurrentWeatherImperial()) { weather ->
                weather?.convert()
            }
    }

    fun getCurrentWeatherMetric(): LiveData<CurrentWeather> {
        return Transformations
            .map(weatherDao.getCurrentWeatherMetric()) { weather ->
                weather?.convert()
            }
    }

    fun getForecastWeatherImperial(): LiveData<List<ForecastWeather>> {
        return Transformations
            .map(weatherDao.getForecastImperial()) { weatherList ->
                weatherList.map {
                    it.convert()
                }
            }
    }

    fun getForecastWeatherMetric(): LiveData<List<ForecastWeather>> {
        return Transformations
            .map(weatherDao.getForecastMetric()) { weatherList ->
                weatherList.map {
                    it.convert()
                }
            }
    }

    suspend fun getWeather(location: String) {
        withContext(Dispatchers.IO) {
            val response = WeatherApi
                .retrofitService
                .getWeatherAsync(API_KEY, location, 5)
            if (response.isSuccessful) {
                val data = response.body()!!
                data.current.location = data.location
                weatherDao.upsertCurrentWeather(data.current)
                weatherDao.clearForecast()
                weatherDao.upsertForecastWeather(data.forecast.forecastday.subList(1, 5))
            }
        }
    }

    suspend fun searchLocation(location: String): List<Location>? {
        return withContext(Dispatchers.IO) {
            WeatherApi
                .retrofitService
                .getSearchLocationAsync(API_KEY, location).body()
        }
    }

    fun getRecentLocations(): LiveData<List<Location>> {
        return weatherDao.getLocation()
    }

    suspend fun addRecentLocations(location: Location) {
        withContext(Dispatchers.IO) {
            weatherDao.insertLocation(location)
        }
    }

    suspend fun deleteRecentLocations(location: Location) {
        withContext(Dispatchers.IO) {
            weatherDao.deleteLocation(location)
        }
    }

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(dao: WeatherDao) =
            instance ?: synchronized(this) {
                instance ?: Repository(dao).also { instance = it }
            }
    }
}
