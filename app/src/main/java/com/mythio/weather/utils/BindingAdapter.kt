package com.mythio.weather.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("resId")
fun imageBinding(img: ImageView, resId: Int) {
    img.setImageResource(resId)
}


@BindingAdapter(value = ["temperature", "unit"], requireAll = true)
fun temperatureTextBinding(txt: TextView, temperature: Double?, unit: Unit?) {
    txt.text = when (unit) {
        Unit.METRIC -> "" + temperature + "\u2103"
        Unit.IMPERIAL -> "" + temperature + "\u2109"
        else -> return
    }
}

@BindingAdapter(value = ["windSpeed", "unit"], requireAll = true)
fun windTextBinding(txt: TextView, windSpeed: Double?, unit: Unit?) {
    txt.text = when (unit) {
        Unit.METRIC -> "" + windSpeed + " kmph"
        Unit.IMPERIAL -> "" + windSpeed + " mph"
        else -> return
    }
}

@BindingAdapter("humidity")
fun humidityTextBinding(txt: TextView, humidity: Int) {
    txt.text = "" + humidity + "%"
}