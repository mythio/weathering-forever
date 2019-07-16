package com.mythio.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mythio.weather.db.model.domain.current.CurrentWeatherImperial
import com.mythio.weather.db.model.domain.current.CurrentWeatherMetric
import com.mythio.weather.db.model.domain.forecast.ForecastWeatherImperial
import com.mythio.weather.db.model.domain.forecast.ForecastWeatherMetric
import com.mythio.weather.db.model.entity.CurrentWeather
import com.mythio.weather.db.model.entity.ForecastWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: CurrentWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertForecast(weather: List<ForecastWeather>)

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeatherMetric(): LiveData<CurrentWeatherMetric>

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeatherImperial(): LiveData<CurrentWeatherImperial>

    @Query("select * from forecast_weather")
    fun getForecastMetric(): LiveData<List<ForecastWeatherMetric>>

    @Query("select * from forecast_weather")
    fun getForecastImperial(): LiveData<List<ForecastWeatherImperial>>
}