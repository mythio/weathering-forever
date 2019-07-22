package com.mythio.weather.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mythio.weather.db.WeatherDatabase
import com.mythio.weather.repository.WeatherRepository
import com.mythio.weather.ui.WeatherViewModel

object InjectorUtils {

    private fun getWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepository.getInstance(
            WeatherDatabase.getInstance(context.applicationContext).weatherDao()
        )
    }

    fun provideWeatherViewModelFactory(context: Context): WeatherViewModelFactory {
        val repository = getWeatherRepository(context)
        return WeatherViewModelFactory(repository)
    }
}

class WeatherViewModelFactory(
    private val repository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {
    
    override fun <T : ViewModel> create(modelClass: Class<T>) = WeatherViewModel(repository) as T
}