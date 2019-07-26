package com.mythio.weather.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mythio.weather.R
import com.mythio.weather.adapter.SearchLocationAdapter
import com.mythio.weather.databinding.ActivitySearchBinding
import com.mythio.weather.utils.InjectorUtils
import com.mythio.weather.viewmodels.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModels {
        InjectorUtils.provideSearchViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val binding: ActivitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.lifecycleOwner = this
        binding.searchViewModel = viewModel
        binding.search.adapter = SearchLocationAdapter(SearchLocationAdapter.OnClickListener { locationR ->

            viewModel.add(locationR)
//            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToWeatherFragment(location))
//            viewModel.clearData()
        })

        viewModel.location.observe(this, Observer {
            viewModel.getSearchData(it)
        })
    }
}
