package com.mythio.weather.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather)

        pref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        binding.lifecycleOwner = this
        binding.weatherViewModel = viewModel

        var location: String = pref.getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!
        val unit: Int = pref.getInt(SHARED_PREF_KEY_UNIT, DEFAULT_UNIT)

//        if (arguments != null) {
//            val arguments = WeatherFragmentArgs.fromBundle(arguments!!)
//            pref.edit().putString(SHARED_PREF_KEY_LOCATION, arguments.locationUrl).apply()
//            location = arguments.locationUrl!!
//            viewModel.updateLocation(location)
//        }

        ib_search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
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
}
