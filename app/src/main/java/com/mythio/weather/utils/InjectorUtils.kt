package com.mythio.weather.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mythio.weather.db.AppDatabase
import com.mythio.weather.repository.Repository
import com.mythio.weather.viewmodels.SearchViewModel
import com.mythio.weather.viewmodels.WeatherViewModel

object InjectorUtils {

    private fun getRepository(context: Context): Repository {
        return Repository.getInstance(
            AppDatabase.getInstance(context.applicationContext).weatherDao()
        )
    }

    fun provideWeatherViewModelFactory(context: Context): WeatherViewModelFactory {
        val repository = this.getRepository(context)
        return WeatherViewModelFactory(repository)
    }

    fun provideSearchViewModelFactory(context: Context): SearchViewModelFactory {
        val repository = this.getRepository(context)
        return SearchViewModelFactory(repository)
    }
}

class WeatherViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = WeatherViewModel(repository) as T
}

class SearchViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) = SearchViewModel(repository) as T
}