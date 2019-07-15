package com.mythio.weather.network.response

import com.google.gson.annotations.SerializedName
import com.mythio.weather.db.entity.CurrentWeather

data class WeatherResponse(

    @SerializedName("current")
    val current: CurrentWeather
)