package com.mythio.weather.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mythio.weather.db.AppDatabase
import com.mythio.weather.repository.SearchRepository
import com.mythio.weather.repository.WeatherRepository
import com.mythio.weather.viewmodels.SearchViewModel
import com.mythio.weather.viewmodels.WeatherViewModel

object InjectorUtils {

    private fun getWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).weatherDao()
        )
    }

    private fun getSearchRepository(context: Context): SearchRepository {
        return SearchRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).locationDao()
        )
    }

    fun provideWeatherViewModelFactory(context: Context): WeatherViewModelFactory {
        val repository = this.getWeatherRepository(context)
        return WeatherViewModelFactory(repository)
    }

    fun provideSearchViewModelFactory(context: Context): SearchViewModelFactory {
        val repository = this.getSearchRepository(context)
        return SearchViewModelFactory(repository)
    }
}

class WeatherViewModelFactory(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = WeatherViewModel(weatherRepository) as T
}

class SearchViewModelFactory(
    private val searchRepository: SearchRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = SearchViewModel(searchRepository) as T
}