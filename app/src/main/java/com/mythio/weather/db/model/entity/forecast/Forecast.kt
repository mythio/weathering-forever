package com.mythio.weather.db.model.entity.forecast

interface Forecast {

    val temperature: Double
    val conditionCode: Int
}