package com.mythio.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mythio.weather.R

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this, MainActivityViewModel.ViewModelFactory(this.application))
            .get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.weather.observe(this, Observer { item ->
            if (item == null) {
                return@Observer
            }
            Log.d("TAG_TAG", "Today temp: " + item.temperature)
        })

        viewModel.weatherForecast.observe(this, Observer { item ->
            if (item == null) {
                return@Observer
            }
            for (forecast in item) {
                Log.d("TAG_TAG", "Future temp: " + forecast.maxtemp)
            }
        })
    }
}