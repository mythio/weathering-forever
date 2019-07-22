package com.mythio.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mythio.weather.db.dao.WeatherDao
import com.mythio.weather.model.entity.CurrentW
import com.mythio.weather.model.entity.ForecastW

@Database(
    entities = [CurrentW::class, ForecastW::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room
                .databaseBuilder(
                    context, WeatherDatabase::class.java, "weather"
                )
                .build()
        }
    }
}
