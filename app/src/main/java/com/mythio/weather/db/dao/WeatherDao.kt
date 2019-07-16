package com.mythio.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mythio.weather.db.model.domain.current.CurrentWeatherUnitImplImperial
import com.mythio.weather.db.model.domain.current.CurrentWeatherUnitImplMetric
import com.mythio.weather.db.model.domain.forecast.FutureWeatherUnitImplImperial
import com.mythio.weather.db.model.domain.forecast.FutureWeatherUnitImplMetric
import com.mythio.weather.db.model.entity.CurrentWeather
import com.mythio.weather.db.model.entity.ForecastWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: CurrentWeather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertForecast(weather: List<ForecastWeather>)

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeatherMetric(): LiveData<CurrentWeatherUnitImplMetric>

    @Query("select * from current_weather where id = 0")
    fun getCurrentWeatherImperial(): LiveData<CurrentWeatherUnitImplImperial>

    @Query("select * from forecast_weather")
    fun getForecastMetric(): LiveData<List<FutureWeatherUnitImplMetric>>

    @Query("select * from forecast_weather")
    fun getForecastImperial(): LiveData<List<FutureWeatherUnitImplImperial>>
}