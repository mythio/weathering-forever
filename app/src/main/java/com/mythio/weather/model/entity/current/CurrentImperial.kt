package com.mythio.weather.model.entity.current

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.mythio.weather.model.entity.Location

data class CurrentImperial(

    @ColumnInfo(name = "tempF")
    override val temperature: Double,

    @ColumnInfo(name = "condition_code")
    override val conditionCode: Int,

    @ColumnInfo(name = "windMph")
    override val windSpeed: Double,

    @ColumnInfo(name = "windDir")
    override val windDirection: String,

    @ColumnInfo(name = "precipMm")
    val precipitation: Double,

    @ColumnInfo(name = "humidity")
    override val humidity: Int,

    @ColumnInfo(name = "feelslikeF")
    override val feelsLikeTemperature: Double,

    @ColumnInfo(name = "uv")
    override val uvIndex: Double,

    @Embedded(prefix = "location_")
    val location: Location
) : Current