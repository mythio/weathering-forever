package com.mythio.weather.db.model.entity.current

interface Current {

    val temperature: Double
    val conditionText: String
    val conditionCode: Int
    val windSpeed: Double
    val windDirection: String
    val humidity: Int
    val feelsLikeTemperature: Double
    val uvIndex: Double
}