package com.mythio.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mythio.weather.db.dao.WeatherDao
import com.mythio.weather.db.entity.CurrentWeather

@Database(
    entities = [CurrentWeather::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDataDao(): WeatherDao

    companion object {

        private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase? {
            if (INSTANCE == null) {
                synchronized(WeatherDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDatabase::class.java, "weather.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
