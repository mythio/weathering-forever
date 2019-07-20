package com.mythio.weather.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener


object BindingAdapter {

    @BindingAdapter("resId")
    @JvmStatic
    fun imageBinding(img: ImageView, resId: Int) {
        img.setImageResource(resId)
    }


    @BindingAdapter(value = ["temperature", "unit"], requireAll = true)
    @JvmStatic
    fun temperatureTextBinding(txt: TextView, temperature: Double?, unit: Unit?) {
        txt.text = when (unit) {
            Unit.METRIC -> "" + temperature + "\u00B0C"
            Unit.IMPERIAL -> "" + temperature + "\u00B0F"
            else -> return
        }
    }

    @BindingAdapter(value = ["windSpeed", "unit"], requireAll = true)
    @JvmStatic
    fun windTextBinding(txt: TextView, windSpeed: Double?, unit: Unit?) {
        txt.text = when (unit) {
            Unit.METRIC -> "" + windSpeed + " km/h"
            Unit.IMPERIAL -> "" + windSpeed + " mi/h"
            else -> return
        }
    }

    @BindingAdapter("humidity")
    @JvmStatic
    fun humidityTextBinding(txt: TextView, humidity: Int) {
        txt.text = "" + humidity + "%"
    }

    @BindingAdapter("searchQuery")
    @JvmStatic
    fun test1(view: EditText, string: String?) {
        if (view.text.toString() != string) {
            view.setText(string)
        }
    }

    @InverseBindingAdapter(attribute = "searchQuery")
    @JvmStatic
    fun test2(ev: EditText): String {

        return ev.text.toString()
    }

    @BindingAdapter("searchQueryAttrChanged")
    @JvmStatic
    fun setListener(view: EditText, listener: InverseBindingListener?) {
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
}