package com.mythio.weather.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.mythio.weather.adapter.SearchLocationAdapter
import com.mythio.weather.network.response.LocationResponse

object BindingAdapter {

    @BindingAdapter("resId")
    @JvmStatic
    fun weatherImageBinding(img: ImageView, resId: Int) {
        img.setImageResource(resId)
    }

    @BindingAdapter(value = ["temperature", "unit"], requireAll = true)
    @JvmStatic
    fun temperatureTextBinding(txt: TextView, temperature: Double?, unit: Unit?) {
        txt.text = when (unit) {
            Unit.METRIC -> "$temperature\u00B0C"
            Unit.IMPERIAL -> "$temperature\u00B0F"
            else -> return
        }
    }

    @BindingAdapter(value = ["windSpeed", "unit"], requireAll = true)
    @JvmStatic
    fun windTextBinding(txt: TextView, windSpeed: Double?, unit: Unit?) {
        txt.text = when (unit) {
            Unit.METRIC -> "$windSpeed km/h"
            Unit.IMPERIAL -> "$windSpeed mi/h"
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
    fun searchBinding2(view: EditText): String {

        return view.text.toString()
    }

    @BindingAdapter("searchQueryAttrChanged")
    @JvmStatic
    fun searchBindingProvider(view: EditText, listener: InverseBindingListener?) {
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
    fun searchRecyclerViewBind(recyclerView: RecyclerView, data: List<LocationResponse>?) {
        val adapter = recyclerView.adapter as SearchLocationAdapter
        adapter.submitList(data ?: listOf())
    }
}