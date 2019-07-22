package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mythio.weather.db.dao.WeatherDao
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.network.WeatherApi
import com.mythio.weather.network.response.Location
import com.mythio.weather.utils.convert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl private constructor(
    private val weatherDao: WeatherDao
) {

    val searchResponse = MutableLiveData<List<Location>>()

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

    suspend fun searchLocation(location: String) {
        searchResponse.value = WeatherApi
            .retrofitService
            .getSearchLocationAsync("70ef3b7f24484a918b782502191207", location)
            .body()!!
    }

    suspend fun getWeather() {
        withContext(Dispatchers.IO) {
            val response = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
            if (response.isSuccessful) {
                val data = response.body()!!
                data.current.location = data.location
                weatherDao.upsertCurrentWeather(data.current)
                weatherDao.upsertForecastWeather(data.forecast.forecastday)
            }
        }
    }

    companion object {

        @Volatile
        private var instance: WeatherRepositoryImpl? = null

        fun getInstance(dao: WeatherDao) =
            instance ?: synchronized(this) {
                instance ?: WeatherRepositoryImpl(dao).also { instance = it }
            }
    }
}
