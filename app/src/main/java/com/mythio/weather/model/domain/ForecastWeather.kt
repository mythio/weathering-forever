package com.mythio.weather.model.domain

import com.mythio.weather.utils.Unit

class ForecastWeather(

    val unit: Unit,
    val temperature: Double,
    val conditionIconRes: Int
)