package com.mythio.weather

import android.app.Application
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        val currentNightMode = newConfig?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        when (currentNightMode) {
            UI_MODE_NIGHT_NO -> {
                Log.d("TAG_TAG_TAG", "NIGHT_OFF")
            } // Night mode is not active, we're using the light theme
            UI_MODE_NIGHT_YES -> {
                Log.d("TAG_TAG_TAG", "NIGHT_ON")
            } // Night mode is active, we're using dark theme
        }
    }
}