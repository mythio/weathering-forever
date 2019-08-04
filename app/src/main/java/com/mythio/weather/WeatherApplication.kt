package com.mythio.weather

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.mythio.weather.utils.SHARED_PREF_KEY_THEME
import com.mythio.weather.utils.SHARED_PREF_NAME

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(
            getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getInt(SHARED_PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        )
    }
}