package com.mythio.weather.db.model.domain.current

interface CurrentWeather {

    val temperature: Double
    val conditionText: String
    val conditionCode: Int
    val windSpeed: Double
    val windDirection: String
    val precipitation: Double
    val feelsLikeTemperature: Double
}