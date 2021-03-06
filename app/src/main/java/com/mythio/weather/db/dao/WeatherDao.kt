package com.mythio.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mythio.weather.model.entity.CurrentW
import com.mythio.weather.model.entity.ForecastW
import com.mythio.weather.model.entity.current.CurrentImperial
import com.mythio.weather.model.entity.current.CurrentMetric
import com.mythio.weather.model.entity.forecast.ForecastImperial
import com.mythio.weather.model.entity.forecast.ForecastMetric

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCurrentWeather(weather: CurrentW)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertForecastWeather(weather: List<ForecastW>)

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeatherMetric(): LiveData<CurrentMetric>

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeatherImperial(): LiveData<CurrentImperial>

    @Query("select * from current_weather where id = 0")
    suspend fun getCurrentWeatherMetricAsync(): CurrentMetric

    @Query("select * from current_weather where id = 0")
    suspend fun getCurrentWeatherImperialAsync(): CurrentImperial

    @Query("select * from forecast_weather")
    fun getForecastMetric(): LiveData<List<ForecastMetric>>

    @Query("select * from forecast_weather")
    fun getForecastImperial(): LiveData<List<ForecastImperial>>

    @Query("select * from forecast_weather")
    fun getForecastMetricAsync(): List<ForecastMetric>

    @Query("select * from forecast_weather")
    fun getForecastImperialAsync(): List<ForecastImperial>

    @Query("delete from forecast_weather")
    fun clearForecast()
}