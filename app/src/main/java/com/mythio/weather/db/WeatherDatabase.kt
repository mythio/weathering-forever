package com.mythio.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mythio.weather.db.dao.WeatherDao
import com.mythio.weather.db.model.entity.CurrentWeather
import com.mythio.weather.db.model.entity.ForecastWeather

@Database(
    entities = [CurrentWeather::class, ForecastWeather::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}

private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "weather"
            ).build()
        }
    }
    return INSTANCE
}

