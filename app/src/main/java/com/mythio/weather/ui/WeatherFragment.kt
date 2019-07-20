package com.mythio.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.mythio.weather.R
import com.mythio.weather.databinding.WeatherFragmentBinding
import com.mythio.weather.utils.Unit
import kotlinx.android.synthetic.main.weather_fragment.*

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, WeatherViewModel.Factory(activity.application))
            .get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: WeatherFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.weather_fragment,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        p.setOnClickListener {
            findNavController().navigate(WeatherFragmentDirections.actionWeatherFragmentToSearchFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getValuesOfUnit(Unit.METRIC)
        viewModel.str.observe(this, Observer {
            Log.d("TAG_TAG", it)
        })
    }
}
