package com.mythio.weather.repository

import androidx.lifecycle.LiveData
import com.mythio.weather.db.dao.LocationDao
import com.mythio.weather.model.entity.Location
import com.mythio.weather.network.WeatherApi
import com.mythio.weather.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository private constructor(
    private val weatherDao: LocationDao
) {

    suspend fun searchLocation(location: String): List<Location>? {
        return withContext(Dispatchers.IO) {
            WeatherApi
                .retrofitService
                .getSearchLocationAsync(API_KEY, location).body()
        }
    }

    fun getRecentLocations(): LiveData<List<Location>> {
        return weatherDao.getLocation()
    }

    suspend fun addRecentLocations(location: Location) {
        withContext(Dispatchers.IO) {
            weatherDao.insertLocation(location)
        }
    }

    suspend fun deleteRecentLocations(location: Location) {
        withContext(Dispatchers.IO) {
            weatherDao.deleteLocation(location)
        }
    }

    companion object {

        @Volatile
        private var instance: SearchRepository? = null

        fun getInstance(dao: LocationDao) =
            instance ?: synchronized(this) {
                instance ?: SearchRepository(dao).also { instance = it }
            }
    }
}
