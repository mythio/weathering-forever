package com.mythio.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mythio.weather.R
import com.mythio.weather.adapter.SearchLocationAdapter
import com.mythio.weather.databinding.FragmentSearchBinding
import com.mythio.weather.utils.InjectorUtils
import com.mythio.weather.utils.debounce

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels {
        InjectorUtils.provideSearchViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchViewModel = viewModel
        binding.search.adapter = SearchLocationAdapter(SearchLocationAdapter.OnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToWeatherFragment(it))
            viewModel.clearData()
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var intSearch = 0
        viewModel.string.debounce(200).observe(viewLifecycleOwner, Observer {
            Log.d("TAG_TAG_TAG", "search term changed " + intSearch++)
            viewModel.getSearchData(it)
        })

        var intRes = 0
        viewModel.searchResults.observe(viewLifecycleOwner, Observer {
            Log.d("TAG_TAG_TAG", "data recieved " + intRes++)
        })
    }
}
