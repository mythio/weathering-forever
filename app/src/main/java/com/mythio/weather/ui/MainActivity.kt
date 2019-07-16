package com.mythio.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mythio.weather.R
import com.mythio.weather.Unit

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this, MainActivityViewModel.ViewModelFactory(this.application))
            .get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getValuesOfUnit(Unit.IMPERIAL)

        viewModel.currentWeatherImperial?.observe(this, Observer {
            if (it == null) {
                Log.d("TAG_TAG_IMPERIAL", "NULL")
                return@Observer
            }
            Log.d("TAG_TAG_IMPERIAL", "OBSERVED")
        })

        viewModel.currentWeatherMetric?.observe(this, Observer {
            if (it == null) {
                Log.d("TAG_TAG_METRIC", "NULL")
                return@Observer
            }
            Log.d("TAG_TAG_METRIC", "OBSERVED")
        })
    }
}