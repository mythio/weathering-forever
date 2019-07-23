package com.mythio.weather.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mythio.weather.R
import com.mythio.weather.databinding.FragmentWeatherBinding
import com.mythio.weather.utils.*
import com.mythio.weather.utils.Unit
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels {

        InjectorUtils.provideWeatherViewModelFactory(requireContext())
    }

    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentWeatherBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather,
            container,
            false
        )

        pref = context!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.weatherViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var location: String = pref.getString(SHARED_PREF_KEY_LOCATION, DEFAULT_LOCATION)!!

        if (arguments != null) {

            val arguments = WeatherFragmentArgs.fromBundle(arguments!!)
            pref.edit().putString(SHARED_PREF_KEY_LOCATION, arguments.locationUrl).apply()
            location = arguments.locationUrl!!
            viewModel.updateLocation(location)
        }

        viewModel.getData(location, Unit.METRIC)

        ib_search.setOnClickListener {

            findNavController().navigate(WeatherFragmentDirections.actionWeatherFragmentToSearchFragment())
        }

        refreshLayout.setOnMultiPurposeListener(object : SimpleMultiPurposeListener() {

            override fun onRefresh(refreshLayout: RefreshLayout) {
                super.onRefresh(refreshLayout)
                viewModel.refreshData(location)
            }
        })

        viewModel.networkState.observe(this, Observer { networkState ->

            when (networkState) {
                NetworkState.FINISH -> {
                    refreshLayout.finishRefresh(200)
                }
                NetworkState.ERROR -> {
                    Toast.makeText(context, "Can't connect to API", Toast.LENGTH_SHORT).show()
                    refreshLayout.finishRefresh(200)
                }
                else -> {

                }
            }
        })
    }
}
