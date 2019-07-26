package com.mythio.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mythio.weather.db.dao.WeatherDao
import com.mythio.weather.model.entity.CurrentW
import com.mythio.weather.model.entity.ForecastW
import com.mythio.weather.model.entity.Location

@Database(
    entities = [CurrentW::class, ForecastW::class, Location::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "weather")
                .build()
        }
    }
}
