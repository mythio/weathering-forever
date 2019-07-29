package com.mythio.weather.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mythio.weather.R
import com.mythio.weather.adapter.RecentLocationAdapter
import com.mythio.weather.adapter.SearchLocationAdapter
import com.mythio.weather.databinding.ActivitySearchBinding
import com.mythio.weather.model.entity.Location
import com.mythio.weather.utils.InjectorUtils
import com.mythio.weather.utils.NetworkState
import com.mythio.weather.utils.SHARED_PREF_KEY_LOCATION
import com.mythio.weather.utils.SHARED_PREF_NAME
import com.mythio.weather.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

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

        binding.rvRecent.adapter = RecentLocationAdapter(object : RecentLocationAdapter.OnClickListener {
            override fun onClick(location: Location) {
                getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit()
                    .putString(SHARED_PREF_KEY_LOCATION, location.name).apply()

                setResult(Activity.RESULT_OK)
                finish()
            }

            override fun onClickDelete(location: Location) {
                viewModel.delete(location)
            }
        })

        binding.rvSearch.adapter = SearchLocationAdapter(object : SearchLocationAdapter.OnClickListener {
            override fun onClick(location: Location) {
                viewModel.add(location)
                getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit()
                    .putString(SHARED_PREF_KEY_LOCATION, location.name).apply()
                setResult(Activity.RESULT_OK)
                finish()
            }
        })

        viewModel.searchLocationQuery.observe(this, Observer {
            viewModel.getSearchData(it)
        })

        viewModel.networkState.observe(this, Observer { networkState ->
            when (networkState) {
                NetworkState.ERROR -> showSnackBar()
                else -> {
                }
            }
        })

        ib_close.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun showSnackBar() {
        Snackbar
            .make(root, "Can't connect", Snackbar.LENGTH_LONG)
            .setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            .show()
    }
}
