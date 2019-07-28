package com.mythio.weather.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mythio.weather.R
import com.mythio.weather.databinding.ActivityWeatherBinding
import com.mythio.weather.utils.*
import com.mythio.weather.viewmodels.WeatherViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels {
        InjectorUtils.provideWeatherViewModelFactory(this)
    }

    private lateinit var pref: SharedPreferences
    private lateinit var location: String
    private var unit: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)

        binding.lifecycleOwner = this
        binding.weatherViewModel = viewModel

        pref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        location = pref.getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!
        unit = pref.getInt(SHARED_PREF_KEY_UNIT, DEFAULT_UNIT)

        ib_search.setOnClickListener {
            startActivityForResult(Intent(this, SearchActivity::class.java), 69)
        }

        refreshLayout.setOnMultiPurposeListener(object : SimpleMultiPurposeListener() {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                super.onRefresh(refreshLayout)
                viewModel.refreshData(location)
            }
        })

        viewModel.getData(location, unit)

        viewModel.networkState.observe(this, Observer { networkState ->
            when (networkState) {
                NetworkState.FINISH -> {
                    refreshLayout.finishRefresh(600)
                }
                NetworkState.ERROR -> {
                    showSnackBar()
                    refreshLayout.finishRefresh(600)
                }
                else -> {
                }
            }
        })
    }

    private fun showSnackBar() {
        Snackbar
            .make(refreshLayout, "Can't connect", Snackbar.LENGTH_LONG)
            .setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setAction("RETRY") { refreshLayout.autoRefresh() }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 69 && resultCode == Activity.RESULT_OK) {
            location = pref.getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!
            viewModel.getData(location, unit)
        } else {
            Log.d("TAG_TAG_TAG", "CANCELLED")
        }
    }
}
