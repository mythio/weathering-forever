package com.mythio.weather

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import com.mythio.weather.utils.SHARED_PREF_KEY_THEME
import com.mythio.weather.utils.SHARED_PREF_NAME

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d(
            "TAG_TAG_TAG", "" + getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getInt(SHARED_PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        )

        Log.d("TAG_TAG_TAG", "NO $MODE_NIGHT_NO")
        Log.d("TAG_TAG_TAG", "YES $MODE_NIGHT_YES")
        Log.d("TAG_TAG_TAG", "SYS $MODE_NIGHT_FOLLOW_SYSTEM")

        AppCompatDelegate
            .setDefaultNightMode(
                getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                    .getInt(SHARED_PREF_KEY_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            )
    }
}