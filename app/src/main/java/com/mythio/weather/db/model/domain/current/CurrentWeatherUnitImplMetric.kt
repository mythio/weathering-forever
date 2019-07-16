package com.mythio.weather.db.model.domain.current

import androidx.room.ColumnInfo

data class CurrentWeatherUnitImplMetric(

    @ColumnInfo(name = "tempC")
    override val temperature: Double,

    @ColumnInfo(name = "condition_text")
    override val conditionText: String,

    @ColumnInfo(name = "windKph")
    override val windSpeed: Double,

    @ColumnInfo(name = "windDir")
    override val windDirection: String,

    @ColumnInfo(name = "precipMm")
    override val precipitation: Double,

    @ColumnInfo(name = "feelslikeC")
    override val feelsLikeTemperature: Double
) : CurrentWeatherUnitImpl