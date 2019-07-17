package com.mythio.weather.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("resId")
fun imageBinding(img: ImageView, resId: Int) {
    img.setImageResource(resId)
}

@BindingAdapter("windDir")
fun windDirectionImageBinding(img: ImageView, windDir: String?) {
    img.rotation = (DIR_TO_DEGREE[windDir] ?: 0).toFloat()
}