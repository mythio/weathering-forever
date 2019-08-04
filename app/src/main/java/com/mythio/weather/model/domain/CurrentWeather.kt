package com.mythio.weather.model.domain

data class CurrentWeather(

    val unit: Int,
    val temperature: Double,
    val conditionText: String,
    val conditionIconRes: Int,
    val windSpeed: Double,
    val precipitation: Double,
    val windDirection: String,
    val humidity: Int,
    val feelsLike: Double,
    val uvIndex: Double,
    val location: String
)