package com.mythio.weather.network.response

import com.mythio.weather.model.entity.CurrentW
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(

    @Json(name = "location")
    val location: Location,

    @Json(name = "current")
    val current: CurrentW,

    @Json(name = "forecast")
    val forecast: ForecastResponse
)