package com.mythio.weather.model.entity.forecast

interface Forecast {
    val date: Long
    val temperature: Double
    val conditionCode: Int
}