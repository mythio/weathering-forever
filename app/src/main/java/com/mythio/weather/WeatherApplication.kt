package com.mythio.weather

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
//            setupWorkManager()
        }
    }

//    private fun setupWorkManager() {
//
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.UNMETERED)
//            .setRequiresCharging(true)
//            .setRequiresBatteryNotLow(true)
//            .setRequiresDeviceIdle(true)
//            .build()
//    }
}