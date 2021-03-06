package com.mythio.weather.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mythio.weather.db.AppDatabase
import com.mythio.weather.repository.WeatherRepository
import com.mythio.weather.utils.DEFAULT_LOCATION
import com.mythio.weather.utils.SHARED_PREF_KEY_LOCATION
import com.mythio.weather.utils.SHARED_PREF_NAME
import retrofit2.HttpException

class RefreshDataWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    companion object {
        const val WORK_NAME = "WEATHER_UPDATE"
    }

    override suspend fun doWork(): Result {

        val repository = WeatherRepository.getInstance(
            AppDatabase.getInstance(applicationContext).weatherDao()
        )

        val location = applicationContext
            .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            .getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!

        try {
            repository.getWeather(location)
        } catch (e: HttpException) {
            return Result.retry()
        }

        return Result.success()
    }
}
