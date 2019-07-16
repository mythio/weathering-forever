package com.mythio.weather.network.response

import com.mythio.weather.db.entity.ForecastWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(

    @Json(name = "forecastday")
    val forecastday: List<ForecastWeather>
)