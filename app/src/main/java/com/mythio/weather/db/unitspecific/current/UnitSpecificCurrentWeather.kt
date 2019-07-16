package com.mythio.weather.db.unitspecific.current

interface UnitSpecificCurrentWeather {

    val temperature: Double
    val conditionText: String
    val windSpeed: Double
    val windDirection: String
    val precipitation: Double
    val feelsLikeTemperature: Double
}