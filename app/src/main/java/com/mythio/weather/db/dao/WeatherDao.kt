package com.mythio.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mythio.weather.CURRENT_WEATHER_ID
import com.mythio.weather.db.entity.CurrentWeather
import com.mythio.weather.db.unitspecific.current.ImperialCurrentWeather
import com.mythio.weather.db.unitspecific.current.MetricCurrentWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: CurrentWeather)

    @Query(value = "select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeather>

    @Query(value = "select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeather>
}