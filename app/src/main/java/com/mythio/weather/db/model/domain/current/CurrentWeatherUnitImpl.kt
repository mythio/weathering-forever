package com.mythio.weather.db.model.domain.current

interface CurrentWeatherUnitImpl {

    val temperature: Double
    val conditionText: String
    val windSpeed: Double
    val windDirection: String
    val precipitation: Double
    val feelsLikeTemperature: Double
}