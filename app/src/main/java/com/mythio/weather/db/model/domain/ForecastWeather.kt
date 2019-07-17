package com.mythio.weather.db.model.domain

import com.mythio.weather.Unit

class ForecastWeather(

    val unit: Unit,
    val temperature: Double,
    val conditionIconRes: Int
)