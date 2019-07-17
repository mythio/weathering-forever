package com.mythio.weather.db.model.domain

import com.mythio.weather.Unit

data class CurrentWeather(

    val unit: Unit,
    val temperature: Double,
    val conditionText: String,
    val conditionIconRes: Int,
    val windSpeed: Double,
    val windDirection: String,
    val humidity: Int,
    val feelsLike: Double,
    val uvIndex: Double
)