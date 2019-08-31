package com.mythio.weather.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.mythio.weather.model.entity.Location
import com.mythio.weather.ui.adapter.RecentLocationAdapter
import com.mythio.weather.ui.adapter.SearchLocationAdapter

object BindingAdapter {

    @BindingAdapter("resId")
    @JvmStatic
    fun weatherImageBinding(img: ImageView, resId: Int) {
        img.setImageResource(resId)
    }

    @BindingAdapter(value = ["temperature", "unit"], requireAll = true)
    @JvmStatic
    fun temperatureTextBinding(txt: TextView, temperature: Double?, unit: Int?) {
        txt.text = when (unit) {
            UNIT_METRIC -> "$temperature\u00B0C"
            UNIT_IMPERIAL -> "$temperature\u00B0F"
            else -> return
        }
    }

    @BindingAdapter(value = ["windSpeed", "unit"], requireAll = true)
    @JvmStatic
    fun windTextBinding(txt: TextView, windSpeed: Double?, unit: Int?) {
        txt.text = when (unit) {
            UNIT_METRIC -> "$windSpeed km/h"
            UNIT_IMPERIAL -> "$windSpeed mi/h"
            else -> return
        }
    }

    @BindingAdapter("humidity")
    @JvmStatic
    fun humidityTextBinding(txt: TextView, humidity: Int) {
        txt.text = "$humidity%"
    }

    @BindingAdapter("searchQuery")
    @JvmStatic
    fun searchBinding(view: EditText, string: String?) {
        if (view.text.toString() != string) {
            view.setText(string)
        }
    }

    @InverseBindingAdapter(attribute = "searchQuery")
    @JvmStatic
    fun searchBinding(view: EditText): String {

        return view.text.toString()
    }

    @BindingAdapter("searchQueryAttrChanged")
    @JvmStatic
    fun searchBinding(view: EditText, listener: InverseBindingListener?) {
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listener?.onChange()
            }
        })
    }

    @BindingAdapter("listData")
    @JvmStatic
    fun recyclerViewBind(recyclerView: RecyclerView, data: List<Location>?) {
        if (recyclerView.adapter is SearchLocationAdapter) {
            val adapter = recyclerView.adapter as SearchLocationAdapter
            adapter.submitList(data ?: listOf())
        } else {
            val adapter = recyclerView.adapter as RecentLocationAdapter
            adapter.submitList(data ?: listOf())
        }
    }

    @BindingAdapter("visibilityRecent")
    @JvmStatic
    fun recentRecyclerViewBinding(view: ConstraintLayout, int: Boolean) {
        if (int) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("visibilitySearch")
    @JvmStatic
    fun searchRecyclerViewBinding(view: ConstraintLayout, int: Boolean) {
        if (!int) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }
}