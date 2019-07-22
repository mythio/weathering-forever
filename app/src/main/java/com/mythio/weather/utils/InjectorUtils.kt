package com.mythio.weather.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.repository.WeatherRepositoryImpl
import com.mythio.weather.ui.WeatherViewModel

object InjectorUtils {

    private fun getWeatherRepository(context: Context): WeatherRepositoryImpl {
        return WeatherRepositoryImpl.getInstance(
            WeatherDatabase.getInstance(context.applicationContext).weatherDao()
        )
    }

    fun provideWeatherViewModelFactory(context: Context): WeatherViewModelFactory {
        val repository = getWeatherRepository(context)
        return WeatherViewModelFactory(repository)
    }
}

class WeatherViewModelFactory(
    private val repository: WeatherRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = WeatherViewModel(repository) as T
}