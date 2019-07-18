package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.model.domain.CurrentWeather
import com.mythio.weather.model.domain.ForecastWeather
import com.mythio.weather.network.WeatherApi
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

    suspend fun getWeatherForecast() {
        withContext(Dispatchers.IO) {
            val response = WeatherApi
                .retrofitService
                .getWeatherAsync("70ef3b7f24484a918b782502191207", "panaji", 7)
            if (response.isSuccessful) {
                database.weatherDao.upsertCurrentWeather(response.body()!!.current)
                database.weatherDao.upsertForecastWeather(response.body()!!.forecast.forecastday)
            } else {
                throw Exception()
            }
        }
    }
}
