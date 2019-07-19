package com.mythio.weather.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mythio.weather.R
import com.mythio.weather.databinding.ActivityMainBinding
import com.mythio.weather.utils.Unit

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

        viewModel.getValuesOfUnit(Unit.METRIC)

        viewModel.searchResult.observe(this, Observer {
            Log.d("TAG_TAG", it[0].name)
        })
    }
}