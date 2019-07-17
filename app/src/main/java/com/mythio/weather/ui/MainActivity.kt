package com.mythio.weather.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mythio.weather.R
import com.mythio.weather.Unit
import com.mythio.weather.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, MainViewModel.ViewModelFactory(this.application))
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getValuesOfUnit(Unit.IMPERIAL)

        viewModel.currentWeather.observe(this, Observer {
            if (it == null) {
                Timber.tag("TAG_TAG_METRIC").d("NULL")
                return@Observer
            }
            Timber.tag("TAG_TAG_METRIC").d("OBSERVED: %s", it.conditionIconRes)
        })
    }
}