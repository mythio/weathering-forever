package com.mythio.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mythio.weather.R
import com.mythio.weather.Unit
import timber.log.Timber

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
                Timber.tag("TAG_TAG_IMPERIAL").d("NULL")
                return@Observer
            }
            Timber.tag("TAG_TAG_IMPERIAL").d("OBSERVED")
        })

        viewModel.currentWeatherMetric?.observe(this, Observer {
            if (it == null) {
                Timber.tag("TAG_TAG_METRIC").d("NULL")
                return@Observer
            }
            Timber.tag("TAG_TAG_METRIC").d("OBSERVED")
        })
    }
}