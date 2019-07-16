package com.mythio.weather.db.model.domain.current

import androidx.room.ColumnInfo

data class CurrentWeatherUnitImplImperial(

    @ColumnInfo(name = "tempF")
    override val temperature: Double,

    @ColumnInfo(name = "condition_text")
    override val conditionText: String,

    @ColumnInfo(name = "windMph")
    override val windSpeed: Double,

    @ColumnInfo(name = "windDir")
    override val windDirection: String,

    @ColumnInfo(name = "precipIn")
    override val precipitation: Double,

    @ColumnInfo(name = "feelslikeF")
    override val feelsLikeTemperature: Double
) : CurrentWeatherUnitImpl