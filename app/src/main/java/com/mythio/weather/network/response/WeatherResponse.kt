package com.mythio.weather.network.response

import com.mythio.weather.db.entity.CurrentWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(

    @Json(name = "current")
    val current: CurrentWeather
)