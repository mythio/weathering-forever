package com.mythio.weather.model.entity.current

import androidx.room.ColumnInfo

data class CurrentMetric(

    @ColumnInfo(name = "tempC")
    override val temperature: Double,

    @ColumnInfo(name = "condition_text")
    override val conditionText: String,

    @ColumnInfo(name = "condition_code")
    override val conditionCode: Int,

    @ColumnInfo(name = "windKph")
    override val windSpeed: Double,

    @ColumnInfo(name = "windDir")
    override val windDirection: String,

    @ColumnInfo(name = "humidity")
    override val humidity: Int,

    @ColumnInfo(name = "feelslikeC")
    override val feelsLikeTemperature: Double,

    @ColumnInfo(name = "uv")
    override val uvIndex: Double
) : Current