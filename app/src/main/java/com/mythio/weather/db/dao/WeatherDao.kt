package com.mythio.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mythio.weather.db.entity.CurrentWeather
import com.mythio.weather.db.unitspecific.current.ImperialCurrentWeather
import com.mythio.weather.db.unitspecific.current.MetricCurrentWeather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: CurrentWeather)

    @Query("select * from current_weather where id = 0")
    fun getWeatherMetric(): LiveData<MetricCurrentWeather>

    @Query("select * from current_weather where id = 0")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeather>

    @Query("select count(*) from current_weather")
    fun getCount(): Int
}