package com.mythio.weather.model.domain

class ForecastWeather(

    val unit: Int,
    val temperature: Double,
    val conditionIconRes: Int,
    val day: String
)